/**
 * Implémentation JDBC de la DAO Utilisateur
 */
package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Article;
import fr.eni.javaee.eni_encheres.bo.Categorie;
import fr.eni.javaee.eni_encheres.dal.CategorieDAO;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;


/**
 * @author vincent
 *
 */
public class CategorieDAOJDBCImpl implements CategorieDAO {
	
	private static String SELECT_ALL_CATEGORIES = "SELECT no_categorie, libelle FROM CATEGORIES;";
	private static String GET_ARTICLE_CATEGORIE = "SELECT c.no_categorie, c.libelle FROM CATEGORIES AS c INNER JOIN ARTICLES_VENDUS AS av ON av.no_categorie = c.no_categorie WHERE av.no_article = ?;";
	
	
	@Override
	public Categorie selectElementById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Categorie> getAllElements() throws BusinessException {
		List<Categorie> listCategories = null;
		
		try (Connection connexion = ConnectionProvider.getConnection()) {
			Statement query = connexion.createStatement();
			ResultSet resultSet = query.executeQuery(SELECT_ALL_CATEGORIES);
			listCategories = new ArrayList<>();
			
			while(resultSet.next()) { 
				int idCategorie = resultSet.getInt("no_categorie");
				String libelle = resultSet.getString("libelle");
				Categorie categorie = new Categorie(idCategorie, libelle);
				listCategories.add(categorie);
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_CATEGORIES_ECHEC);
			throw businessException;
		}
		return listCategories;
	}

	@Override
	public Categorie getArticleCategorie(int idArticle) throws BusinessException {
		Categorie categorie = null;
		
		try (Connection connexion = ConnectionProvider.getConnection();) {
			PreparedStatement query = connexion.prepareStatement(GET_ARTICLE_CATEGORIE);
			query.setInt(1, idArticle);
			ResultSet result = query.executeQuery();  
			result.next();
			int idCategorie = result.getInt("no_categorie");
			String libelle = result.getString("libelle");
			categorie = new Categorie(idCategorie, libelle);
						
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_ECHEC);
			throw businessException;
		}
		return categorie;
	}
	
	@Override
	public void createElement(Categorie element) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateElement(Categorie element) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElementById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
}
