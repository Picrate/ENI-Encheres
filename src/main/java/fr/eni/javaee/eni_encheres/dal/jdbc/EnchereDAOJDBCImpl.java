package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Enchere;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.EnchereDAO;

public class EnchereDAOJDBCImpl implements EnchereDAO {
	private static final String BEST_ENCHERE_FOR_ONE_ARTICLE = "SELECT montant_enchere, no_utilisateur FROM ENCHERES WHERE montant_enchere = (SELECT max(montant_enchere) FROM ENCHERES WHERE no_article = ?) AND no_article = ?;";
	private static final String DELETE_BY_USER_ID = "DELETE FROM ENCHERES WHERE no_utilisateur = ?;";
	private final static String CREATE_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?);";
	private static final String SELECT_ENCHERE = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur = ? AND no_article = ?;";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ?;"; 
	
	private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
	
	
	@Override
	public void createElement(Enchere element) throws BusinessException {
		if (element == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(CREATE_ENCHERE);
			pstmt.setInt(1, element.getNo_utilisateur());
			pstmt.setInt(2, element.getNo_article());
			pstmt.setTimestamp(3, Timestamp.valueOf(element.getDateEnchere()), calendar);
			pstmt.setInt(4, element.getMontantEnchere());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public Enchere selectElementById(int id) throws BusinessException {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
		throw businessException;
	}

	@Override
	public Enchere selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
		throw businessException;
	}

	@Override
	public void updateElement(Enchere element) throws BusinessException {
		if (element == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
			pstmt.setTimestamp(1, Timestamp.valueOf(element.getDateEnchere()), calendar);
			pstmt.setInt(2, element.getMontantEnchere());
			pstmt.setInt(3, element.getNo_utilisateur());
			pstmt.setInt(4, element.getNo_article());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		
	}

	@Override
	public void deleteElementById(int id) throws BusinessException {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
		throw businessException;
		
	}

	@Override
	public List<Enchere> getAllElements() throws BusinessException {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
		throw businessException;
	}

	@Override
	public void deleteEnchereByUtilisateurId(int userId) throws BusinessException {
		if (Objects.isNull(userId)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;

				pstmt = cnx.prepareStatement(DELETE_BY_USER_ID);
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
	
	@Override
	public Enchere bestEnchereForArticle(int idArticle) throws BusinessException {
		Enchere enchere = null;
		try (Connection connexion = ConnectionProvider.getConnection();) {
			// Get user winner
			PreparedStatement query = connexion.prepareStatement(BEST_ENCHERE_FOR_ONE_ARTICLE);
			query.setInt(1, idArticle);
			query.setInt(2, idArticle);
			ResultSet result = query.executeQuery();
			if (result.next()) {
				int idUserWinner = result.getInt("no_utilisateur");
				int montantEnchere = result.getInt("montant_enchere");
				enchere = new Enchere (idUserWinner, idArticle, montantEnchere);
			}				
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
		return enchere;
	}

	@Override
	public Enchere getEnchere(int userId, int articleId) throws BusinessException {
		Enchere enchere = null;
		
		if (Objects.isNull(userId) || Objects.isNull(articleId) ) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw businessException;
		}		
		
		try (Connection connexion = ConnectionProvider.getConnection();) {
			
			PreparedStatement query = connexion.prepareStatement(SELECT_ENCHERE);
			query.setInt(1, userId);
			query.setInt(2, articleId);
			ResultSet result = query.executeQuery();
			if (result.next()) {
				enchere= new Enchere(result.getInt("no_utilisateur"), result.getInt("no_article"), result.getTimestamp("date_enchere", calendar).toLocalDateTime(), result.getInt("montant_enchere"));
			}				
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET_ECHEC);
			throw businessException;
		}
		return enchere;
	}

}
