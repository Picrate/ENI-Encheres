package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.util.List;

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
			if ((boolean)session.getAttribute("connecte")) {
				//System.out.println("Utilisateur connecté : " + session.getAttribute("connecte"));
				request.setAttribute("userConnected", session.getAttribute("connecte"));
			}		
		}
		
		/*
		 * get liste d'objets
		 */		
		ArticleManager articleManager = new ArticleManager();
		try {
			List<Article> listeArticle = articleManager.getAllArticles();
			request.setAttribute("listeArticles", listeArticle);
		} catch (BusinessException e) {
			e.printStackTrace();
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
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Home.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// get articles in categorie
		
		// get article with keyword
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Home.jsp");
		requestDispatcher.forward(request, response);
	}

}
