/**
 * Gestionnaire d'authentification utilisateur
 */
package fr.eni.javaee.eni_encheres.bll;

import java.util.Objects;

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
	
	/**
	 * Authentifie un compte en fonction de l'email ou du pseudo
	 * @param username l'email ou le login de l'utilsateur
	 * @param password le mot de passe fournit dans le formulaire d'authentification
	 * @return true si le mot de passe correspond / false sinon
	 * @throws BusinessException
	 */
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
		if (Objects.nonNull(utilisateur)) {
			if (utilisateur.getPassword().contentEquals(password)) {
				authenticate=true;
			}
		} else {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.REQUESTED_USER_IS_NULL);
			throw be;
		}
		
		return authenticate;		
	}
	
	
}
