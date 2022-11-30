package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Categorie;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.ArticleManager;
import fr.eni.javaee.eni_encheres.bll.CategorieManager;
import fr.eni.javaee.eni_encheres.bll.LoginManager;
import fr.eni.javaee.eni_encheres.bll.UtilisateurManager;


/**
 * Servlet implementation class AjouterVente
 */
@WebServlet("/ajouter-un-article")
public class ArticleFormulaireServlet extends HttpServlet  {
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
		
		System.out.println(request.getParameter("articleId"));
		/* 
		 * 
		 * delete article
		 * 
		 */
		if (request.getParameter("deleteId") != null && request.getParameter("deleteId") != "") {
			try {
				int articleId = Integer.parseInt(request.getParameter("deleteId"));
				try {
					ArticleManager.getInstance().deleteArticle(articleId);
					response.sendRedirect("/ENI-Encheres/home");
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
		}
		// Modify article
		else if (request.getParameter("articleId") != null && request.getParameter("articleId") != "") {
			System.out.println("Modify");
			try {
				int articleId = Integer.parseInt(request.getParameter("articleId"));
				try {
					Article article = ArticleManager.getInstance().getArticleByID(articleId);
					request.setAttribute("selectedArticle", article);	
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/ArticleFormulaire.jsp");
					requestDispatcher.forward(request, response);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} 
		// Create
		else if (request.getParameter("nomArticle") != null && request.getParameter("nomArticle") != "") {
			System.out.println("Create");
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			LocalDateTime dateDebutEnchere = LocalDateTime.parse(request.getParameter("dateDebutEnchere"));
			LocalDateTime dateFinEnchere = LocalDateTime.parse(request.getParameter("dateFinEnchere"));
			int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
			int noUtilisateur = currentUser.getNo_utilisateur();
			int noCategorie = Integer.parseInt(request.getParameter("selectedCategorie"));
			Article article = new Article(nomArticle, description, dateDebutEnchere, dateFinEnchere, miseAPrix, 0, noUtilisateur, noCategorie);	
			try {
				ArticleManager.getInstance().addArticle(article);
				request.setAttribute("articleId", article.getNoArticle());	
				response.sendRedirect("/ENI-Encheres/home");
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/ArticleFormulaire.jsp");
			requestDispatcher.forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
