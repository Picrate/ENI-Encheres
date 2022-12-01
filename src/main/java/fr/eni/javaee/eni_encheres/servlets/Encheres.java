package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.EnchereManager;
import fr.eni.javaee.eni_encheres.bll.UtilisateurManager;
import fr.eni.javaee.eni_encheres.bo.Enchere;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.messages.LecteurMessage;

/**
 * Servlet implementation class Enchere
 */
@WebServlet("/encherir")
public class Encheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.getServletContext().getRequestDispatcher("/article").forward(request, response);;
	}

	/**
	 * Gestion de l'action enchérir
	 * On vérifie que l'utilsateurConnecte a suffisament de crédit et si son offre est supérieure à la dernière enchère.
	 * Si c'est le cas, on créé une nouvelle enchère à son nom.
	 * Si il est déjà le meilleur enchérisseur, on modifie l'enchère précédente au lieu de la créer.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int articleId = Integer.valueOf(request.getParameter("articleId"));
		int montantEnchere = Integer.valueOf(request.getParameter("enchere"));
		HttpSession session = request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
		try {

			if (UtilisateurManager.getInstance().checkSufficentsCredits(utilisateurConnecte, montantEnchere)) {
				if (EnchereManager.getInstance().validateMontantEnchere(articleId, montantEnchere)) {
					if (EnchereManager.getInstance().aUneEnchere(utilisateurConnecte.getNo_utilisateur(), articleId)) {
						Enchere lastEnchere = EnchereManager.getInstance().getEnchere(utilisateurConnecte.getNo_utilisateur(), articleId);

						lastEnchere.setMontantEnchere(montantEnchere);
						EnchereManager.getInstance().updateEnchere(lastEnchere);
					} else {
						EnchereManager.getInstance().createEnchere(utilisateurConnecte.getNo_utilisateur(), articleId, montantEnchere);
					}					
					//UtilisateurManager.getInstance().removeCredits(utilisateurConnecte, Integer.valueOf(montantEnchere));
					response.sendRedirect("article?articleId="+articleId);
				} else {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.OFFER_TOO_LOW);
					throw be;
				}
			} else {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatServlets.NOT_ENOUGH_CREDITS);
				throw be;
			}
			
		} catch (BusinessException e) {
			List<Integer> listeCodeErreurs = e.getListeCodesErreur();
			List<String> listeErreurs = new ArrayList<String>();
			for (Integer codeErreur : listeCodeErreurs) {
				listeErreurs.add(LecteurMessage.getMessageErreur(codeErreur));
				request.setAttribute("listeErreurs", listeErreurs);
				this.getServletContext().getRequestDispatcher("/article").forward(request, response);
				
			}
		}				
	}

}
