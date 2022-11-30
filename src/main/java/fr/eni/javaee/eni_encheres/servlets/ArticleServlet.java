package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Categorie;
import fr.eni.javaee.eni_encheres.bo.Enchere;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.messages.LecteurMessage;
import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.ArticleManager;
import fr.eni.javaee.eni_encheres.bll.CategorieManager;
import fr.eni.javaee.eni_encheres.bll.EnchereManager;
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
		
		
		if (request.getParameter("enchere") != null && request.getParameter("enchere") != "")
			{
			
			}
		
		try {
			int articleId = Integer.parseInt(request.getParameter("articleId"));

				Article article = ArticleManager.getInstance().getArticleByID(articleId);
				request.setAttribute("selectedArticle", article);
				
				Categorie categorie = CategorieManager.getInstance().getArticleCategorie(articleId);
				request.setAttribute("selectedCategorie", categorie);
				
				Utilisateur utilisateur = UtilisateurManager.getInstance().getUtilisateurById(article.getNoUtilisateur());
				request.setAttribute("seller", utilisateur);
				
				Enchere bestOffer = EnchereManager.getInstance().bestEnchereForArticle(articleId);
				if (Objects.nonNull(bestOffer)) {
					request.setAttribute("bestOffer", bestOffer.getMontantEnchere());
				
					Utilisateur buyer = UtilisateurManager.getInstance().getUtilisateurById(bestOffer.getNo_utilisateur());
					request.setAttribute("buyerPseudo", buyer.getPseudo());
				}				
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatServlets.UNHANDLED_EXCEPTION);
			List<Integer> listeCodeErreurs = be.getListeCodesErreur();
			List<String> listeErreurs = new ArrayList<String>();
			for (Integer codeErreur : listeCodeErreurs) {
				listeErreurs.add(LecteurMessage.getMessageErreur(codeErreur));
				request.setAttribute("listeErreurs", listeErreurs);
			}
		} finally {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/Article.jsp");
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
