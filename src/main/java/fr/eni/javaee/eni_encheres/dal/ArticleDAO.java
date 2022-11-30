package fr.eni.javaee.eni_encheres.dal;

import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Enchere;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> getArticlesInCategorie(int idCategorie) throws BusinessException;
	public List<Article> getUserArticlesEnchere(int idUser) throws BusinessException;
	public void deleteAllArticlesByUserId(int userId) throws BusinessException;
	public List<Article> getUserWinArticle(int idUser) throws BusinessException;
	public List<Article> getEndedSells(int idUser) throws BusinessException;
	public Enchere bestEnchereForArticle(int idArticle) throws BusinessException;
}
