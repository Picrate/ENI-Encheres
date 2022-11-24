package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Categorie;

public interface CategorieDAO extends DAO<Categorie> {
	
	public Categorie getArticleCategorie(int idArticle) throws BusinessException;
	
}
