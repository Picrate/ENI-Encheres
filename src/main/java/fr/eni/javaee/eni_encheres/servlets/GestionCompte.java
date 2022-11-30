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
	 * Si on vient de l'URL /compte/supprimer, on supprime le compte utilisateur,
	 * l'adresse, les enchères et les articles associés. Sinon on redirige sur la
	 * page de compte par défaut.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/compte/supprimer")) {
			HttpSession session = request.getSession();
			Utilisateur currentUser = (Utilisateur) session.getAttribute("utilisateurConnecte");

			try {
				UtilisateurManager.getInstance().deleteUtilisateur(currentUser);
				response.sendRedirect(this.getServletContext().getContextPath() + "/deconnexion");

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
	 * Gestion de l'authentification - Validation concordance mots de passe
	 * formulaire - validation utilsateur existe
	 * 
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

			/*
			 * Si on vient de l'URL /compte/creer
			 */
			if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/compte/creer")) {

				Utilisateur newUtilisateur;

				if (!LoginManager.getInstance().checkPasswordMatch(password, cpassword)) { // Si les mots de passe ne
																							// correspondent pas
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.PASSWORD_MISMATCH);
					throw be;
				} else if (LoginManager.getInstance().checkEmailExists(email)) { // Si l'email n'existe pas
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.EMAIL_ALREADY_EXISTS);
					throw be;
				} else if (LoginManager.getInstance().checkPseudoExists(pseudo)) { // Si le pseudo n'existe pas
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.PSEUDO_ALREADY_EXISTS);
					throw be;
				} else { // Le compte est valide
					// On créé l'adresse
					Adresse newAdresse = new Adresse(rue, Integer.valueOf(codePostal), ville);
					AdresseManager.getInstance().createNewAdresse(newAdresse);
					newUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,
							LoginManager.getInstance().getBase64Password(password), newAdresse);
					UtilisateurManager.getInstance().createNewUtilisateur(newUtilisateur);

					// On redirige vers la page d'accueil
					this.getServletContext().getRequestDispatcher("/").forward(request, response);

				}

				/*
				 * Si on vient de l'URL /compte/modifier
				 */
			} else if (request.getRequestURI()
					.equalsIgnoreCase(this.getServletContext().getContextPath() + "/compte/modifier")) {

				HttpSession session = request.getSession();
				String newPassword = null;
				Utilisateur currentUser = (Utilisateur) session.getAttribute("utilisateurConnecte");
				Adresse currentAdresse = currentUser.getAdresse();

				/*
				 * Si lemot de passe a été modifié
				 */
				if (password.length > 0) {
					// On vérifie que les mots de passe correspondent dans le formulaire
					if (!LoginManager.getInstance().checkPasswordMatch(password, cpassword)) {
						BusinessException be = new BusinessException();
						be.ajouterErreur(CodesResultatServlets.PASSWORD_MISMATCH);
						throw be;
						// On encode le nouveau mot de passe
					} else {
						newPassword = LoginManager.getInstance().getBase64Password(password);
						currentUser.setPassword(newPassword);
					}

					/*
					 * Mise à jour des inforamtions utilisateur + adresse
					 */
					currentAdresse.setRue(rue);
					currentAdresse.setCodePostal(Integer.valueOf(codePostal));
					currentAdresse.setVille(ville);
					AdresseManager.getInstance().updateAdresse(currentAdresse);

					currentUser.setPseudo(pseudo);
					currentUser.setNom(nom);
					currentUser.setPrenom(prenom);
					currentUser.setEmail(email);
					currentUser.setPassword(newPassword);
					currentUser.setTelephone(telephone);
					currentUser.setAdresse(currentAdresse);
					UtilisateurManager.getInstance().updateUtilisateur(currentUser);

					// Redirection vers la page d'accueil
					this.getServletContext().getRequestDispatcher("/").forward(request, response);
				}
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
