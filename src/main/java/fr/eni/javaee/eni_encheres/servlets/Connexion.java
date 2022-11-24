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
@WebServlet("/connexion")
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
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
		String password = request.getParameter("password");
		Cookie authCookie = null;

		System.out.println(identifiant);
		System.out.println(password);
		
		HttpSession session = request.getSession();

		try {

			if (!identifiant.contentEquals("") || !password.contentEquals("") || Objects.nonNull(identifiant) || Objects.nonNull(password)) {

				boolean authStatus = LoginManager.getInstance().authenticateUser(identifiant, password);
				// si utilisateur authentifié avec succes
				if (authStatus) {
					// Déclaration Attributs Session
					session.setAttribute("connecte", true);
					session.setAttribute("utilisateur",
							UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(identifiant));

					// recherche cookie
					if (cookieExists("authenticated", request)) {
						authCookie = getCookie("authenticated", request);
					}
					if (authCookie != null) {
						if (authCookie.getValue().equalsIgnoreCase("true")) {
							this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request,
									response);
						} else {
							authCookie.setMaxAge(0); // Efface le cookie
							response.addCookie(authCookie);
							session.invalidate();
							this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,
									response);
						}

					} else {
						authCookie = new Cookie("authenticated", "true");
						authCookie.setHttpOnly(true);
						authCookie.setMaxAge(604800); // 7 jours
						response.addCookie(authCookie);
						this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
					}

				} else {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatServlets.USERNAME_OR_PASSWORD_INVALID);
					throw be;
				}
			} else {
				BusinessException usernameOrPasswordNullException = new BusinessException();
				usernameOrPasswordNullException.ajouterErreur(CodesResultatServlets.USERNAME_OR_PASSWORD_INVALID);
				throw usernameOrPasswordNullException;
			}
		} catch (BusinessException e) {
			List<Integer> listeCodeErreurs = e.getListeCodesErreur();
			List<String> listeErreurs = new ArrayList<String>();
			for (Integer codeErreur : listeCodeErreurs) {
				listeErreurs.add(LecteurMessage.getMessageErreur(codeErreur));
				System.out.println("Erreurs: " + listeErreurs.toString());
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
