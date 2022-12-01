package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eni_encheres.bll.ArticleManager;
import fr.eni.javaee.eni_encheres.bll.CategorieManager;
import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Categorie;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.BusinessException;


/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utilisateur currentUser = null;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		/* 
		 * 
		 * get current user statut 
		 * 
		 */
		// if session exist
		boolean connectedUser = false;
		if (request.getSession(false) != null) {
			//System.out.println("session");
			HttpSession session = request.getSession();
			// if user connected
			if (session.getAttribute("connecte") != null) {
				if ((boolean)session.getAttribute("connecte")) {
					//System.out.println("Utilisateur connecté : " + session.getAttribute("connecte"));
					connectedUser = true;
					currentUser = (Utilisateur)session.getAttribute("utilisateurConnecte");
				}
			} 
		}
		
		/* 
		 * 
		 * get liste categories 
		 * 
		 */
		try {
			List<Categorie> listeCategories = CategorieManager.getInstance().getAllCategories();
			request.setAttribute("listeCategories", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		/*
		 * 
		 * create liste articles 
		 * 
		 */		
		Map<Article, Utilisateur> listeArticles = new TreeMap<>();
		Map<Article, Utilisateur> tempListeArticles = new TreeMap<>();
		tempListeArticles.clear();
		
		// Si search or 
		if (request.getParameter("selectedCategorie") != null && request.getParameter("selectedCategorie") != "" && request.getParameter("selectedCategorie") != "0") {
			//System.out.println(request.getParameter("selectedCategorie"));
			try {
				int selectedCategorieId = Integer.parseInt(request.getParameter("selectedCategorie"));
				request.setAttribute("selectedCategorieId", selectedCategorieId);
				
				try {
					// all articles
					if (selectedCategorieId == 0) {
						listeArticles = ArticleManager.getInstance().getAllArticlesMap();
					} 
					// articles in categorie
					else {
						listeArticles = ArticleManager.getInstance().getArticlesbyCategorieMap(selectedCategorieId);
					}
					
					// articles with search
					if (request.getParameter("searchPattern") != "" && request.getParameter("searchPattern") != null) {
						String searchPattern = (String)request.getParameter("searchPattern").trim().toLowerCase();
						request.setAttribute("searchPattern", searchPattern);
						listeArticles.forEach((article, utilisateur) -> {
							if (article.getNomArticle().toLowerCase().contains(searchPattern) ) {
								tempListeArticles.put(article, utilisateur); 
							} 
					    });
						listeArticles = tempListeArticles;
					}
					request.setAttribute("listeArticles", listeArticles);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		// Si pas de post param
		else {
			try {
				listeArticles = ArticleManager.getInstance().getAllArticlesMap();
				request.setAttribute("listeArticles", listeArticles);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		/*
		 * 
		 * articles filters for connected user
		 * 
		 */
		if (request.getParameter("userFilter") != "" && request.getParameter("userFilter") != null && connectedUser) {
			tempListeArticles.clear();
			String filterValue = request.getParameter("userFilter");
			request.setAttribute("userFilterAttribute", filterValue);
			
			// Define now datetime
			LocalDateTime now = LocalDateTime.now();
			//System.out.println(now);
			
			switch (filterValue) {
				// get articles en cours d'enchere
				case "openEncheres":
				listeArticles.forEach((article, utilisateur) -> {
					//System.out.println(article.getNomArticle() + " : " + article.getDateDebutEncheres() + " - " + article.getDateDebutEncheres().isBefore(now));
					if (article.getDateDebutEncheres().isBefore(now) && article.getDateFinEncheres().isAfter(now)) {
						//System.out.println(article.getNomArticle());
						tempListeArticles.put(article, utilisateur); 
					}
			    });
				listeArticles = tempListeArticles;
				break;
				
				// get articles encheris
				case "currentEncheres":
				try {
					listeArticles = ArticleManager.getInstance().getArticlesByUserEnchere(currentUser.getNo_utilisateur());
					listeArticles.forEach((article, utilisateur) -> {
						if (article.getDateFinEncheres().isAfter(now)) {
							tempListeArticles.put(article, utilisateur); 
						}
					});
					listeArticles = tempListeArticles;
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				break;
				
				// get articles remportés
				case "winEncheres":
				try {
					listeArticles = ArticleManager.getInstance().getUserWinArticle(currentUser.getNo_utilisateur());
					listeArticles.forEach((article, utilisateur) -> {
						System.out.println("test" + article.getNoUtilisateur());
					});
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				break;
				
				// get article vendus
				case "currentSells":
				listeArticles.forEach((article, utilisateur) -> {
					if (utilisateur.getNo_utilisateur() == currentUser.getNo_utilisateur() && article.getDateDebutEncheres().isBefore(now) && article.getDateFinEncheres().isAfter(now)) {
						tempListeArticles.put(article, utilisateur); 
					}
			    });
				listeArticles = tempListeArticles;
				break;
				
				// get article ventes non débutées
				case "notStartSells":
				listeArticles.forEach((article, utilisateur) -> {
					if (utilisateur.getNo_utilisateur() == currentUser.getNo_utilisateur() && article.getDateDebutEncheres().isAfter(now)) {
						tempListeArticles.put(article, utilisateur); 
					}
			    });
				listeArticles = tempListeArticles;
				break;
				
				// get article ventes en cours
				case "endingSells":
				listeArticles.forEach((article, utilisateur) -> {
					if (utilisateur.getNo_utilisateur() == currentUser.getNo_utilisateur() && article.getDateFinEncheres().isBefore(now)) {
						tempListeArticles.put(article, utilisateur);
					}
			    });
				listeArticles = tempListeArticles;
				break;				
			}
			request.setAttribute("listeArticles", listeArticles);
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Home.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
