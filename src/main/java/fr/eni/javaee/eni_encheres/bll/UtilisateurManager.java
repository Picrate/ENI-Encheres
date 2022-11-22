package fr.eni.javaee.eni_encheres.bll;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;
import fr.eni.javaee.eni_encheres.dal.UtilisateurDAO;


/**
 * Gestion des utilisateurs
 * Singleton
 * @author patrice
 *
 */
public class UtilisateurManager {
	
	private static UtilisateurManager instance = null;;
	private UtilisateurDAO utilisateurDAO;
	
	
	/**
	 * Constructeur Singleton
	 */
	private UtilisateurManager() {
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	
	/**
	 * Récupération de l'instance du singleton
	 * @return le singleton
	 */
	public static UtilisateurManager getInstance() {
		if (instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	/**
	 * Récupération d'un utilsateur par son email ou son pseudo
	 * @param pseudoOrEmail le pseudo ou l'email de l'utilsateur
	 * @return L'utilisateur
	 * @throws BusinessException
	 */
	public Utilisateur getUtilisateurByPseudoOrEmail(String pseudoOrEmail) throws BusinessException {
		Utilisateur utilisateur = null;
		
		if(pseudoOrEmail == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_OR_EMAIL_NULL);
			throw businessException;
		}
		else {
			if (pseudoOrEmail.contains("@")) {
				utilisateur = this.utilisateurDAO.getUtilisateurByMail(pseudoOrEmail);
			} else {
				utilisateur = this.utilisateurDAO.getUtilisateurByPseudo(pseudoOrEmail);
			}
			
		}
		return utilisateur;

	}

}
