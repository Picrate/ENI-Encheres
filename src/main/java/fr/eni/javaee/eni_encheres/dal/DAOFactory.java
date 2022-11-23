package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.dal.jdbc.ArticleDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO()
	{
		return new UtilisateurDAOJDBCImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}
	
}
