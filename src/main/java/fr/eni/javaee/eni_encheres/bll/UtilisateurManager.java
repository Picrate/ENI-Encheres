package fr.eni.javaee.eni_encheres.bll;

import java.util.Objects;

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
	
	public boolean checkUserExists(String PseudoOrEmail) throws BusinessException {
		boolean checkStatus = false;
		Utilisateur checkedUtilisateur = this.getUtilisateurByPseudoOrEmail(PseudoOrEmail);
		if (Objects.nonNull(checkedUtilisateur)) {
			checkStatus = true;
		}		
		return checkStatus;
	}

}
