package fr.eni.javaee.eni_encheres.bll;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Enchere;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;
import fr.eni.javaee.eni_encheres.dal.EnchereDAO;

public class EnchereManager {

	
	private static EnchereManager instance = null;
	private EnchereDAO dao;
	
	private EnchereManager() {
		this.dao = DAOFactory.getEnchereDAO();
	}
	
	public static EnchereManager getInstance() {
		if (instance==null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void deleteEncheresByUserId(int userId) throws BusinessException {
		this.dao.deleteEnchereByUtilisateurId(userId);
	}
	
	/**
	 * 
	 * @param articleId
	 * @return
	 * @throws BusinessException
	 */
	public Enchere bestEnchereForArticle(int articleId) throws BusinessException {
		Enchere enchere = null;
		enchere = this.dao.bestEnchereForArticle(articleId);
		return enchere;
	}
	
}
