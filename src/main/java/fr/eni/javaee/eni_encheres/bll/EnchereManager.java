package fr.eni.javaee.eni_encheres.bll;

import java.time.LocalDateTime;
import java.util.Objects;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Enchere;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;
import fr.eni.javaee.eni_encheres.dal.EnchereDAO;

public class EnchereManager {

	
	private static EnchereManager instance = null;
	private EnchereDAO dao;
	
	private EnchereManager() {
		this.dao = DAOFactory.getEnchereDAO();
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static EnchereManager getInstance() {
		if (instance==null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	
	/**
	 * Supprime les enchères d'un utilsateur.
	 * @param userId
	 * @throws BusinessException
	 */
	public void deleteEncheresByUserId(int userId) throws BusinessException {
		this.dao.deleteEnchereByUtilisateurId(userId);
	}
	
	/**
	 * Récupère l'enchère de l'utilsateur sur l'objet.
	 * @param userId
	 * @param articleId
	 * @return
	 * @throws BusinessException
	 */
	public Enchere getEnchere(int userId, int articleId) throws BusinessException {
		return this.dao.getEnchere(userId, articleId);
	}
	
	/**
	 * Vérifie si l'utilisateur à une enchere en cours pour l'article.
	 * @param userId l'identifiant de l'utilisateur
	 * @param articleId l'article de l'utilisateur
	 * @return true si il a déjà enchéri sur l'article / false sinon
	 * @throws BusinessException
	 */
	public boolean aUneEnchere(int userId,int articleId) throws BusinessException {
		boolean checkStatus = false;
		if (Objects.nonNull(this.dao.getEnchere(userId, articleId))) {
			checkStatus = true;
		}		
		return checkStatus;
	}
	
	/**
	 * Récupère l'enchère possédant la meilleure offre pour un article
	 * @param articleId l'identifiant de l'article à rechercher
	 * @return la meilleure enchère pour l'article
	 * @throws BusinessException
	 */
	public Enchere bestEnchereForArticle(int articleId) throws BusinessException {
		Enchere enchere = null;
		enchere = this.dao.bestEnchereForArticle(articleId);
		return enchere;
	}
	
	/**
	 * Vérifie si le montant de l'enchère soummise est supérieure à la meilleure enchère proposée pour l'article
	 * @param articleId l'identifiant de l'article sur lequel a été posé l'enchère
	 * @param montantEnchere le montant proposé
	 * @return true si montant > meilleure enchère / false sinon
	 * @throws BusinessException
	 */
	public boolean validateMontantEnchere(int articleId, int montantEnchere) throws BusinessException {
		boolean checkStatus = false;
		Enchere enchere = this.bestEnchereForArticle( Integer.valueOf(articleId));
		if ( Objects.isNull(enchere) || montantEnchere  >= enchere.getMontantEnchere()) {
			checkStatus = true;			
		}		
		return checkStatus;
	}
	
	/**
	 * Créé une nouvelle enchère pour l'article par l'utilisateur.
	 * @param userId l'encherisseur
	 * @param articleId l'article aux encheres.
	 * @param montant le montant de l'enchère.
	 * @return la nouvelle enchère.
	 * @throws BusinessException
	 */
	public Enchere createEnchere( int userId, int articleId, int montant) throws BusinessException {
		
		if(Objects.isNull(userId) || Objects.isNull(articleId) || Objects.isNull(montant) ) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.NULL_OR_BLANK_PARAMETER);
			throw be;
		}
		
		Enchere enchere = new Enchere(userId,articleId,montant);
		this.dao.createElement(enchere);
		return enchere;
	}
	
	/**
	 * Met à jour l'enchère
	 * @param enchere l'enchère à mettre à jour
	 * @throws BusinessException
	 */
	public void updateEnchere(Enchere enchere) throws BusinessException {
		enchere.setDateEnchere(LocalDateTime.now());
		this.dao.updateElement(enchere);
	}
	
}
