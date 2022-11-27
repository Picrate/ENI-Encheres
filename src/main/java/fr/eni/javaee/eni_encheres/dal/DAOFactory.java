package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.bo.Adresse;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.jdbc.AdresseDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.ArticleDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.CategorieDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.CryptoDAOJDBCImpl;
import fr.eni.javaee.eni_encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public abstract class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO(){
		return new UtilisateurDAOJDBCImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOJDBCImpl();
	}
	
	public static DAO<Adresse> getAdresseDAO(){
		return new AdresseDAOJDBCImpl();
	}
	
	public static CryptoDAO getCryptoDAO() {
		return new CryptoDAOJDBCImpl();
	}
	
}
