package fr.eni.javaee.eni_encheres.bll;

import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Categorie;
import fr.eni.javaee.eni_encheres.dal.ArticleDAO;
import fr.eni.javaee.eni_encheres.dal.CategorieDAO;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;


/**
 * 
 * Articles manager
 * 
 * @author vincent
 *
 */
public class CategorieManager {
	
	private static CategorieManager instance = null;
	private CategorieDAO categorieDAO;
	
	
	/**
	 * Constructeur Singleton
	 */
	public CategorieManager() {
		this.categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	
	/**
	 * 
	 * Récupération de l'instance du singleton
	 * 
	 * @return le singleton
	 */
	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	/**
	 * 
	 * Get article categorie
	 * 
	 * @param idArticle
	 * @return
	 * @throws BusinessException
	 */
	public Categorie getArticleCategorie (int idArticle) throws BusinessException {
		Categorie categorie = null;
		categorie = this.categorieDAO.getArticleCategorie(idArticle);
		return categorie;
	}
	
	/**
	 * 
	 * Get all categories
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Categorie> getAllCategories () throws BusinessException {
		List<Categorie> listCategories = null;
		listCategories = this.categorieDAO.getAllElements();
		return listCategories;
	}

}
