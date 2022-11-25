package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		/* 
		 * get current user statut 
		 */
		// if session exist
		if (request.getSession(false) != null) {
			//System.out.println("session");
			HttpSession session = request.getSession();
			// if user connected
			if (session.getAttribute("connecte") != null) {
				if ((boolean)session.getAttribute("connecte")) {
					//System.out.println("Utilisateur connecté : " + session.getAttribute("connecte"));
					//request.setAttribute("userConnected", session.getAttribute("connecte"));
				}
			} 
		}
		
		/* 
		 * get liste categories 
		 */
		CategorieManager categorieManager = new CategorieManager();
		try {
			List<Categorie> listeCategories = categorieManager.getAllCategories();
			request.setAttribute("listeCategories", listeCategories);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		// Si categorie post param
		if (request.getParameter("selectedCategorie") != null && request.getParameter("selectedCategorie") != "" && request.getParameter("selectedCategorie") != "0") {
			try {
				int selectedCategorieId = Integer.parseInt(request.getParameter("selectedCategorie"));
				request.setAttribute("selectedCategorieId", selectedCategorieId);
				
				try {
					List<Article> listeArticles = null;
					List<Article> tempListeArticles = new ArrayList<>();
					
					// all articles
					if (selectedCategorieId == 0) {
						ArticleManager articleManager = new ArticleManager();
						listeArticles = articleManager.getAllArticles();
					} 
					// articles in categorie
					else {
						ArticleManager articleManager = new ArticleManager();
						listeArticles = articleManager.getArticlesbyCategorie(selectedCategorieId);
					}
					
					// get article with keyword
					if (request.getParameter("searchPattern") != "" && request.getParameter("searchPattern") != null) {
						String searchPattern = (String)request.getParameter("searchPattern").trim();
						request.setAttribute("searchPattern", searchPattern);
						int i = 0;
						for (Article article : listeArticles) {
							if (article.getNomArticle().contains(searchPattern) ) {
								tempListeArticles.add(article);
							}
							i += 1;
						}
						listeArticles = tempListeArticles;
					}
					
					// 
					if (request.getParameter("userFilter") != "" && request.getParameter("userFilter") != null) {
						
					}
					// get articles en cours d'enchere
					
					
					// get articles encheris
					// get articles remportés
					
					// get article vendus
					// get article ventes non débutées
					// get article ventes en cours
					
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
			/*
			 * get liste d'objets
			 */	
			try {
				ArticleManager articleManager = new ArticleManager();
				Map<Article, Utilisateur> listeArticle = articleManager.getAllArticlesMap();
				request.setAttribute("listeArticles", listeArticle);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
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
