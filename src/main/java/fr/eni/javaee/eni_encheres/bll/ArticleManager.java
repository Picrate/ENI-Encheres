package fr.eni.javaee.eni_encheres.bll;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.ArticleDAO;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;


/**
 * 
 * Articles manager
 * 
 * @author vincent
 *
 */
public class ArticleManager {
	
	private static ArticleManager instance = null;
	private ArticleDAO articleDAO;
	
	
	/**
	 * Constructeur Singleton
	 */
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	
	/**
	 * 
	 * Récupération de l'instance du singleton
	 * 
	 * @return le singleton
	 */
	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	/**
	 * 
	 * Get article by ID
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Article getArticleByID (int id) throws BusinessException {
		Article article = null;
		article = this.articleDAO.selectElementById(id);
		return article;
	}
	
	/**
	 * 
	 * Get all articles
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Article> getAllArticles () throws BusinessException {
		List<Article> listArticles = null;
		listArticles = this.articleDAO.getAllElements();
		return listArticles;
	}
	
	/**
	 * 
	 * Get articles by categorie
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Article> getArticlesbyCategorie (int idCategorie) throws BusinessException {
		List<Article> listArticles = null;
		listArticles = this.articleDAO.getArticlesInCategorie(idCategorie);
		return listArticles;
	}
	
	/**
	 * 
	 * Get all articles
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public Map<Article, Utilisateur> getAllArticlesMap () throws BusinessException {
		Map<Article, Utilisateur> listArticles = new HashMap<>();
		List <Article> listArticleTemp = null;
		listArticleTemp = this.articleDAO.getAllElements();
		for (Article article : listArticleTemp) {
			Utilisateur user = UtilisateurManager.getInstance().getUtilisateurById(article.getNoUtilisateur());
			listArticles.putIfAbsent(article, user);
		}
		return listArticles;
	}
	
	/**
	 * 
	 * Get articles by categorie
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public Map<Article, Utilisateur> getArticlesbyCategorieMap (int idCategorie) throws BusinessException {
		Map<Article, Utilisateur> listArticles = new HashMap<>();
		List <Article> listArticleTemp = null;
		listArticleTemp = this.articleDAO.getArticlesInCategorie(idCategorie);
		for (Article article : listArticleTemp) {
			Utilisateur user = UtilisateurManager.getInstance().getUtilisateurById(article.getNoUtilisateur());
			listArticles.putIfAbsent(article, user);
		}
		return listArticles;
	}

}
