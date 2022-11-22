/**
 * 
 */
package fr.eni.javaee.eni_encheres.bll;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;

/**
 * Classe de gestion de l'authentification d'un utilisateur.
 * @author patrice
 *
 */
public class LoginManager {

	private static LoginManager instance = null;

	
	private LoginManager() {

	}
	
	public static LoginManager getInstance(){
		if(instance == null) {
			instance = new LoginManager();
		}
		return instance;
	}
	
	public boolean authenticateUser(String username, String password) throws BusinessException {
		boolean authenticate = false;
		if (username == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_OR_EMAIL_NULL);
			throw businessException;
		} else if (password == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.PASSWORD_IS_NULL);
			throw businessException;
		}		
		Utilisateur utilisateur = UtilisateurManager.getInstance().getUtilisateurByPseudoOrEmail(username);
		if (utilisateur.getPassword().contentEquals(password)) {
			authenticate=true;
		}
		return authenticate;		
	}
	
	
}
