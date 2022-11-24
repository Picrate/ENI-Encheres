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
import fr.eni.javaee.eni_encheres.dal.ArticleDAO;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;


/**
 * @author vincent
 *
 */
public class ArticleDAOJDBCImpl implements ArticleDAO {

	private static String SELECT_ALL_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS;";
	private static String SELECT_ARTICLE_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article = ?;";
	
	@Override
	public Article selectElementById(int id) throws BusinessException {
		Article article = null;
		
		try (Connection connexion = ConnectionProvider.getConnection();) {
			PreparedStatement query = connexion.prepareStatement(SELECT_ARTICLE_BY_ID);
			query.setInt(1, id);
			ResultSet result = query.executeQuery();  
			result.next();
			int idArticle = result.getInt("no_article");
			String nomArticle = result.getString("nom_article");
			String descriptionArticle = result.getString("description");
			Date dateDebutEnchere = result.getDate("date_debut_encheres");
			Date dateFinEnchere = result.getDate("date_fin_encheres");
			int prixInitialArticle = result.getInt("prix_initial");
			int prixVenteArticle = result.getInt("prix_vente");
			int userArticle = result.getInt("no_utilisateur");
			int categorieArticle = result.getInt("no_categorie");
			article = new Article(idArticle, nomArticle, descriptionArticle, dateDebutEnchere, dateFinEnchere, prixInitialArticle, prixVenteArticle, userArticle, categorieArticle);
						
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_ECHEC);
			throw businessException;
		}
		
		return article;
	}	
	
	@Override
	public List<Article> getAllElements() throws BusinessException {
		List<Article> listArticles = null;

		try (Connection connexion = ConnectionProvider.getConnection()) {
			Statement query = connexion.createStatement();
			ResultSet resultSet = query.executeQuery(SELECT_ALL_ARTICLES);
			listArticles = new ArrayList<>();
			
			while(resultSet.next()) { 
				int idArticle = resultSet.getInt("no_article");
				String nomArticle = resultSet.getString("nom_article");
				String descriptionArticle = resultSet.getString("description");
				Date dateDebutEnchere = resultSet.getDate("date_debut_encheres");
				Date dateFinEnchere = resultSet.getDate("date_fin_encheres");
				int prixInitialArticle = resultSet.getInt("prix_initial");
				int prixVenteArticle = resultSet.getInt("prix_vente");
				int userArticle = resultSet.getInt("no_utilisateur");
				int categorieArticle = resultSet.getInt("no_categorie");
				Article article = new Article(idArticle, nomArticle, descriptionArticle, dateDebutEnchere, dateFinEnchere, prixInitialArticle, prixVenteArticle, userArticle, categorieArticle);
				listArticles.add(article);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_ARTICLES_ECHEC);
			throw businessException;
		}
		
		return listArticles;
	}
	
	@Override
	public void createElement(Article element) throws BusinessException {
		
	}


	@Override
	public void deleteElementById(int id) throws BusinessException {		
	}


	@Override
	public void updateElement(Article element) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	


}
