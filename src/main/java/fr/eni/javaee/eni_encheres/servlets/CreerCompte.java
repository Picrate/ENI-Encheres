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

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.AdresseManager;
import fr.eni.javaee.eni_encheres.bll.LoginManager;
import fr.eni.javaee.eni_encheres.bll.UtilisateurManager;
import fr.eni.javaee.eni_encheres.bo.Adresse;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.messages.LecteurMessage;

/**
 * Servlet implementation class CreerCompte
 */
@WebServlet("/creercompte")
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		Utilisateur newUtilisateur;

		try {
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

			this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);

		} catch (BusinessException e) {
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
