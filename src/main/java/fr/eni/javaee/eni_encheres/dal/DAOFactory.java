package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.jdbc.ArticleDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public abstract class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO()
	{
		return new UtilisateurDAOJDBCImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJDBCImpl();
	}
	
}
