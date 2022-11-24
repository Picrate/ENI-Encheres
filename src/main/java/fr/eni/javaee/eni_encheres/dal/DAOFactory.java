package fr.eni.javaee.eni_encheres.dal;

<<<<<<< HEAD
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.jdbc.ArticleDAOJDBCImpl;
>>>>>>> 933b90975f2238412e16fdd0980568855e152269
import fr.eni.javaee.eni_encheres.dal.jdbc.UtilisateurDAOJDBCImpl;

public abstract class DAOFactory {
	
	public static DAO<Utilisateur> getUtilisateurDAO()
	{
		return new UtilisateurDAOJDBCImpl();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJDBCImpl();
	}
	
}
