package fr.eni.javaee.eni_encheres.dal;

import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Article;

public interface ArticleDAO extends DAO<Article> {
	public List<Article> getArticlesInCategorie(int idCategorie) throws BusinessException;
}
