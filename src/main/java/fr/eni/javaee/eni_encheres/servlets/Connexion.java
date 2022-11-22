package fr.eni.javaee.eni_encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bll.CodesResultatBLL;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("password");
		Cookie authCookie = null;
		
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if( cookies[i].getName().equalsIgnoreCase("authenticated")){
				authCookie = cookies[i];
			}
		}
		
		try {
			boolean authStatus = LoginManager.getInstance().authenticateUser(identifiant, password);
			if(authStatus) {
				if(authCookie !=  null) {
					this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
				} else {
					authCookie = new Cookie("authenticated", "true");
					response.addCookie(authCookie);
					this.getServletContext().getRequestDispatcher("/WEB-INF/Home.jsp").forward(request, response);
				}
				
			} else {
				request.setAttribute("errorMessage", LecteurMessage.getMessageErreur(CodesResultatServlets.USERNAME_OR_PASSWORD_INVALID));
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);

			}
		} catch (BusinessException e) {
			
			e.printStackTrace();			
		}
		
		
	}

}
