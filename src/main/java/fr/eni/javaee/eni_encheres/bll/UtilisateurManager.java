package fr.eni.javaee.eni_encheres.bll;

import java.util.Objects;

import org.apache.jasper.tagplugins.jstl.Util;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Adresse;
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
	 * Récupération d'un utilisateur par son email ou son pseudo
	 * @param pseudoOrEmail le pseudo ou l'email de l'utilsateur
	 * @return L'utilisateur
	 * @throws BusinessException
	 */
	public Utilisateur getUtilisateurByPseudoOrEmail(String pseudoOrEmail) throws BusinessException {
		Utilisateur utilisateur = null;
		Adresse adresse = null;
		
		if(pseudoOrEmail == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_OR_EMAIL_NULL);
			throw businessException;
		}
		else {
			if (pseudoOrEmail.contains("@")) {
				utilisateur = this.utilisateurDAO.getUtilisateurByMail(pseudoOrEmail);
				if (Objects.nonNull(utilisateur)) {
					adresse = AdresseManager.getInstance().getAdresseById(this.utilisateurDAO.getUtilisateurAdresseId(utilisateur.getNo_utilisateur()));
					utilisateur.setAdresse(adresse);
				}

			} else {
				utilisateur = this.utilisateurDAO.getUtilisateurByPseudo(pseudoOrEmail);
				if (Objects.nonNull(utilisateur)) {
					adresse = AdresseManager.getInstance().getAdresseById(this.utilisateurDAO.getUtilisateurAdresseId(utilisateur.getNo_utilisateur()));
					utilisateur.setAdresse(adresse);
				}				
			}			
		}
		return utilisateur;

	}
	/**
	 * Recupère un utilisateur par son id
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Utilisateur getUtilisateurById(int id) throws BusinessException {
		Utilisateur utilisateur = this.utilisateurDAO.selectElementById(id);
		if (Objects.nonNull(utilisateur)) {
			Adresse adresse = AdresseManager.getInstance().getAdresseById(this.utilisateurDAO.getUtilisateurAdresseId(utilisateur.getNo_utilisateur()));
			utilisateur.setAdresse(adresse);
		}
		return utilisateur;
	}
	
	/**
	 * Creation d'un nouvel utilisateur
	 * @param utilisateur l'utilsateur à créer
	 * @throws BusinessException
	 */
	public void createNewUtilisateur(Utilisateur utilisateur) throws BusinessException{
		this.utilisateurDAO.createElement(utilisateur);
	}
	
	/**
	 * Vérifie si l'utilsateur Existe dans la base.
	 * @param PseudoOrEmail l'utilsateur à rechercher
	 * @return true si l'utilsateur existe / false sinon.
	 * @throws BusinessException
	 */
	public boolean checkUserExists(String PseudoOrEmail) throws BusinessException {
		boolean checkStatus = false;
		Utilisateur checkedUtilisateur = this.getUtilisateurByPseudoOrEmail(PseudoOrEmail);
		if (Objects.nonNull(checkedUtilisateur)) {
			checkStatus = true;
		}		
		return checkStatus;
	}
	
	/**
	 * Suppression de l'utilisateur
	 * @param utilisateur l'utilsateur à supprimer
	 * @throws BusinessException
	 */
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDAO.deleteElementById(utilisateur.getNo_utilisateur());
	}
	
	/**
	 * Mise à jour des inforamtions de l'utilisateur.
	 * @param utilisateur l'utilsateur à mettre à jour.
	 * @throws BusinessException
	 */
	public void updateUtilisateur(Utilisateur utilisateur) throws BusinessException {
		this.utilisateurDAO.updateElement(utilisateur);
	}
	
	public boolean checkSufficentsCredits(Utilisateur utilisateur, int amount) {
		boolean checkStatus = false;
		if (utilisateur.getCredit() >= amount)
		{
			checkStatus = true;
		}
		return checkStatus;
	}
	
	public void removeCredits(Utilisateur utilisateur, int credits) throws BusinessException {
		utilisateur.setCredit(utilisateur.getCredit()-credits);
		this.utilisateurDAO.updateElement(utilisateur);
	}

}
