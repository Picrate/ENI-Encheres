/**
 * Implémentation JDBC de la DAO Utilisateur
 */
package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

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
	private static String GET_ARTICLES_IN_CATEGORIE = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_categorie = ?;";
	private static String GET_USER_ARTICLES_ENCHERES = "SELECT a.no_article, a.nom_article, a.description, a.date_debut_encheres, a.date_fin_encheres, a.prix_initial, a.prix_vente, a.no_utilisateur, a.no_categorie FROM ARTICLES_VENDUS AS a INNER JOIN ENCHERES AS e ON e.no_article = a.no_article WHERE e.no_utilisateur = ?";
	private static String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?)";
	private static String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	private static final String DELETE_ALL_ARTICLES_BY_USER_ID = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";
	private static final String DELETE_ALL_ARTICLES_BY_USER_ID = "DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?;";

	private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
	
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
			LocalDateTime dateDebutEnchere = result.getTimestamp("date_debut_encheres", calendar).toLocalDateTime();
			LocalDateTime dateFinEnchere = result.getTimestamp("date_fin_encheres", calendar).toLocalDateTime();
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
				LocalDateTime dateDebutEnchere = resultSet.getTimestamp("date_debut_encheres", calendar).toLocalDateTime();
				LocalDateTime dateFinEnchere = resultSet.getTimestamp("date_fin_encheres", calendar).toLocalDateTime();
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
	public List<Article> getArticlesInCategorie(int idCategorie) throws BusinessException {
		List<Article> listArticles = null;

		try (Connection connexion = ConnectionProvider.getConnection()) {
			PreparedStatement query = connexion.prepareStatement(GET_ARTICLES_IN_CATEGORIE);
			query.setInt(1, idCategorie);
			ResultSet resultSet = query.executeQuery();  
			listArticles = new ArrayList<>();
			
			while(resultSet.next()) { 
				int idArticle = resultSet.getInt("no_article");
				String nomArticle = resultSet.getString("nom_article");
				String descriptionArticle = resultSet.getString("description");
				LocalDateTime dateDebutEnchere = resultSet.getTimestamp("date_debut_encheres", calendar).toLocalDateTime();
				LocalDateTime dateFinEnchere = resultSet.getTimestamp("date_fin_encheres", calendar).toLocalDateTime();
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
	public List<Article> getUserArticlesEnchere(int idUser) throws BusinessException {
		List<Article> listArticles = null;
		
		try (Connection connexion = ConnectionProvider.getConnection()) {
			PreparedStatement query = connexion.prepareStatement(GET_USER_ARTICLES_ENCHERES);
			query.setInt(1, idUser);
			ResultSet resultSet = query.executeQuery();  
			listArticles = new ArrayList<>();
			
			while(resultSet.next()) { 
				int idArticle = resultSet.getInt("no_article");
				String nomArticle = resultSet.getString("nom_article");
				String descriptionArticle = resultSet.getString("description");
				LocalDateTime dateDebutEnchere = resultSet.getTimestamp("date_debut_encheres", calendar).toLocalDateTime();
				LocalDateTime dateFinEnchere = resultSet.getTimestamp("date_fin_encheres", calendar).toLocalDateTime();
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
			businessException.ajouterErreur(CodesResultatDAL.SELECT_USER_ARTICLES_ENCHERES_ECHEC);
			throw businessException;
		}
		
		return listArticles;
	}
	
	@Override
	public void createElement(Article element) throws BusinessException {
		if (element == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, element.getNomArticle());
			pstmt.setString(2, element.getDescription());
			pstmt.setTimestamp(3, Timestamp.valueOf(element.getDateDebutEncheres()));
			pstmt.setTimestamp(4, Timestamp.valueOf(element.getDateFinEncheres()));
			pstmt.setInt(5, element.getMiseAPrix());
			pstmt.setInt(6, element.getPrixVente());
			pstmt.setInt(7, element.getNoUtilisateur());
			pstmt.setInt(8, element.getNoCategorie());
			
			int nbRows = pstmt.executeUpdate();
			
			if (nbRows == 1){
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()){
					element.setNoArticle(rs.getInt(1));
				}
			}
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
	}

	@Override
	public void deleteElementById(int id) throws BusinessException {
		try (Connection connexion = ConnectionProvider.getConnection();) {
			System.out.println(id);
			PreparedStatement preparedStatement = connexion.prepareStatement(DELETE_ARTICLE);
			preparedStatement.setInt(1, id);
			int row = preparedStatement.executeUpdate();  
			System.out.println(row);
						
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
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

	@Override
	public void deleteAllArticlesByUserId(int userId) throws BusinessException {
		if (Objects.isNull(userId)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;

				pstmt = cnx.prepareStatement(DELETE_ALL_ARTICLES_BY_USER_ID);
				pstmt.setInt(1, userId);
				pstmt.executeUpdate();
				
				pstmt.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
				throw businessException;
			}
		}
		
	}

	

	
}
