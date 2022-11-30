package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Categorie;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.ArticleManager;
import fr.eni.javaee.eni_encheres.bll.CategorieManager;
import fr.eni.javaee.eni_encheres.bll.UtilisateurManager;


/**
 * Servlet implementation class AjouterVente
 */
@WebServlet("/article")
public class ArticleServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//System.out.println(request.getParameter("articleId"));
		
		try {
			int articleId = Integer.parseInt(request.getParameter("articleId"));
			try {
				Article article = ArticleManager.getInstance().getArticleByID(articleId);
				request.setAttribute("selectedArticle", article);
				
				Categorie categorie = CategorieManager.getInstance().getArticleCategorie(articleId);
				request.setAttribute("selectedCategorie", categorie);
				
				Utilisateur utilisateur = UtilisateurManager.getInstance().getUtilisateurById(Integer.valueOf(article.getNoUtilisateur()));
				request.setAttribute("seller", utilisateur);
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Article.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
