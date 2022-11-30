package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.AdresseManager;
import fr.eni.javaee.eni_encheres.bll.ArticleManager;
import fr.eni.javaee.eni_encheres.bll.EnchereManager;
import fr.eni.javaee.eni_encheres.bll.LoginManager;
import fr.eni.javaee.eni_encheres.bll.UtilisateurManager;
import fr.eni.javaee.eni_encheres.bo.Adresse;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.messages.LecteurMessage;

/**
 * Servlet implementation class CreerCompte
 */
@WebServlet(urlPatterns = { "/compte/creer", "/compte/modifier", "/compte/supprimer" })
public class GestionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/compte/supprimer")) {
			HttpSession session = request.getSession();
			Utilisateur currentUser = (Utilisateur) session.getAttribute("utilisateurConnecte");

			try {

				// Violation contrainte enchere
				// TODO Implémenter l'enchere manager pour supprimer toutes les encheres d'un
				// utilisateur
				// TODO Implémenter la suppression des tous les articles d'un utilsateur pour
				// suppression
				int currentUserId = currentUser.getNo_utilisateur();
				EnchereManager.getInstance().deleteEncheresByUserId(currentUserId);	
				ArticleManager.getInstance().deleteAllArticlesByUserId(currentUserId);
				UtilisateurManager.getInstance().deleteUtilisateur(currentUser);					
						
				response.sendRedirect(this.getServletContext().getContextPath()+"/deconnexion");
			} catch (BusinessException e) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatServlets.DELETE_USER_ERROR);
				e.printStackTrace();
			}
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/Compte.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email").toLowerCase();
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		char[] password = request.getParameter("password").toCharArray();
		char[] cpassword = request.getParameter("cpassword").toCharArray();

		try {

			if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/compte/creer")) {
				Utilisateur newUtilisateur;
				if (!LoginManager.getInstance().checkPasswordMatch(password, cpassword)) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.PASSWORD_MISMATCH);
					throw be;
				}

				if (LoginManager.getInstance().checkEmailExists(email)) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.EMAIL_ALREADY_EXISTS);
					throw be;
				}
				if (LoginManager.getInstance().checkPseudoExists(pseudo)) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.PSEUDO_ALREADY_EXISTS);
					throw be;
				}
				// On créé l'adresse
				Adresse newAdresse = new Adresse(rue, Integer.valueOf(codePostal), ville);
				AdresseManager.getInstance().createNewAdresse(newAdresse);
				newUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,
						LoginManager.getInstance().getBase64Password(password), newAdresse);
				UtilisateurManager.getInstance().createNewUtilisateur(newUtilisateur);

				this.getServletContext().getRequestDispatcher("/").forward(request, response);
				
								
			} else if (request.getRequestURI() // Si on modifie le compte utilisateur
					.equalsIgnoreCase(this.getServletContext().getContextPath() + "/compte/modifier")) {
								
				HttpSession session = request.getSession();
				String newPassword = null;
				Utilisateur currentUser = (Utilisateur) session.getAttribute("utilisateurConnecte");
				Adresse currentAdresse = currentUser.getAdresse();

				if (password.length > 0) {
					if (!LoginManager.getInstance().checkPasswordMatch(password, cpassword)) {
						BusinessException be = new BusinessException();
						be.ajouterErreur(CodesResultatServlets.PASSWORD_MISMATCH);
						throw be;
					} else {
						newPassword = LoginManager.getInstance().getBase64Password(password);
					}					

					
				} else {
					newPassword = currentUser.getPassword();
				}
				
				Adresse newAdresse = new Adresse(currentAdresse.getId(), rue, Integer.valueOf(codePostal), ville);
				AdresseManager.getInstance().updateAdresse(newAdresse);
				Utilisateur updatedUtilisateur = new Utilisateur(currentUser.getNo_utilisateur(), pseudo, nom, prenom, email, telephone, newPassword, currentUser.getCredit(), currentUser.isAdministrateur(), newAdresse);
				UtilisateurManager.getInstance().updateUtilisateur(updatedUtilisateur);
				
				this.getServletContext().getRequestDispatcher("/").forward(request, response);
				
			}

		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> listeCodeErreurs = e.getListeCodesErreur();
			List<String> listeErreurs = new ArrayList<String>();
			for (Integer codeErreur : listeCodeErreurs) {
				listeErreurs.add(LecteurMessage.getMessageErreur(codeErreur));
				request.setAttribute("listeErreurs", listeErreurs);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Compte.jsp").forward(request, response);
			}
		}

	}

}
