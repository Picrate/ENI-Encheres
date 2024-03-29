package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.LoginManager;
import fr.eni.javaee.eni_encheres.bll.UtilisateurManager;
import fr.eni.javaee.eni_encheres.messages.LecteurMessage;

/**
 * Servlet implementation class Connexion
 */
@WebServlet(urlPatterns = { "/connexion", "/deconnexion" })
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Renvoie à la page de connexion
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Si on vient de l'URL /connexion On redirige Vers la page de connexion

		if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/connexion")) {
			HttpSession session = request.getSession(true);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
		} // Sinon Si on vient de l'URL /deconnexion On invalide la session et on
			// redirirge vers l'accueil
		else if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/deconnexion")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(this.getServletContext().getContextPath());

		}

	}

	/**
	 * Méthode de connexion via le formulaire Fait appel au LoginManager pour
	 * Verifier l'authentification de l'utilsateur Si auth = KO, On redirige vers la
	 * page de connexion avec Mersage d'erreur Si Auth OK: On définit trois
	 * variables de session: - authenticated: boolean / l'authentification de
	 * l'utilisateur a r�ussi - userId: l'identifiant unique de l'utilisateur. -
	 * pseudo: String / le pseudo de l'utilisateur On vérifie l'existence du cookie
	 * authCookie et sa valeur: - Si authCookie n'existe pas, on le créé et on place
	 * sa valeur à true. On l'ajoute aux cookies présents. - Si authCookie existe et
	 * que valeur = true alors on redirige vers la page d'accueil - Si la valeur de
	 * authCookie est différente: - on désactive le cookie - on invalide la session
	 * - on redirige vers la page de connexion
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String identifiant = request.getParameter("identifiant");
		char[] password = request.getParameter("password").toCharArray();

		HttpSession session = request.getSession();

		try {

			if (request.getRequestURI().equalsIgnoreCase(this.getServletContext().getContextPath() + "/connexion")) {
				
				// Si identifiant ou mot de passe vide
				if (identifiant.isBlank() || password.length == 0) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.USERNAME_OR_PASSWORD_INVALID);
					throw be;
				}

				if (UtilisateurManager.getInstance().checkUserExists(identifiant)) {
					boolean authStatus = LoginManager.getInstance().authenticateUser(identifiant, password);
					
					// si utilisateur authentifié avec succes
					if (authStatus) {
						// Déclaration Attributs Session
						session.setAttribute("connecte", true);
						session.setAttribute("utilisateurConnecte",
								UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(identifiant));
						response.sendRedirect(this.getServletContext().getContextPath());

					} else {
						BusinessException usernameOrPasswordNullException = new BusinessException();
						usernameOrPasswordNullException
								.ajouterErreur(CodesResultatServlets.USERNAME_OR_PASSWORD_INVALID);
						throw usernameOrPasswordNullException;
					}
				/*
				 * l'utilisateur n'existe pas on lance une exception
				 */
				} else {
					BusinessException usernameOrPasswordNullException = new BusinessException();
					usernameOrPasswordNullException.ajouterErreur(CodesResultatServlets.USERNAME_OR_PASSWORD_INVALID);
					throw usernameOrPasswordNullException;
				}
			}
		} catch (BusinessException e) {
			List<Integer> listeCodeErreurs = e.getListeCodesErreur();
			List<String> listeErreurs = new ArrayList<String>();
			for (Integer codeErreur : listeCodeErreurs) {
				listeErreurs.add(LecteurMessage.getMessageErreur(codeErreur));
				request.setAttribute("listeErreurs", listeErreurs);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
			}

		}
	}

	/**
	 * Méthode de vérification de l'existence d'un cookie
	 * 
	 * @param cookieName le nom du cookie à rechercher dans la requete
	 * @param request    la requete ou chercher le cookie
	 * @return true si le cookie existe / false sinon
	 */
	private boolean cookieExists(String cookieName, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();

		boolean cookieExists = false;

		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equalsIgnoreCase(cookieName)) {
				cookieExists = true;
			}
		}
		return cookieExists;
	}

	/**
	 * M�thode de récupération d'un cookie par son nom
	 * 
	 * @param cookieName le nom du cookie à récupérer
	 * @param request    la requete ou chercher le cookie
	 * @return Le cookie si il existe / NULL sinon
	 */
	private Cookie getCookie(String cookieName, HttpServletRequest request) {

		Cookie[] cookies = request.getCookies();
		Cookie requestedCookie = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equalsIgnoreCase(cookieName)) {
				requestedCookie = cookies[i];
			}
		}
		return requestedCookie;
	}

}
