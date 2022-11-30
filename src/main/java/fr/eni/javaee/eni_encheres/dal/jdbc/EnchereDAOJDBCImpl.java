package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Enchere;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.EnchereDAO;

public class EnchereDAOJDBCImpl implements EnchereDAO {
	private static String BEST_ENCHERE_FOR_ONE_ARTICLE = "SELECT montant_enchere, no_utilisateur FROM ENCHERES WHERE montant_enchere = (SELECT max(montant_enchere) FROM ENCHERES WHERE no_article = ?);";
	private static final String DELETE_BY_USER_ID = "DELETE FROM ENCHERES WHERE no_utilisateur = ?;";
	
	@Override
	public void createElement(Enchere element) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere selectElementById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateElement(Enchere element) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteElementById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enchere> getAllElements() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
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
			ResultSet result = query.executeQuery();
			//System.out.println("test3" + result.next());
			if (result.next()) {
				System.out.println("test4");
				System.out.println("test2" + result.getInt("no_utilisateur"));
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
		
		//System.out.println("test2" + enchere.getNo_article());
		return enchere;
	}

}
