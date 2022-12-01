package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.UserParameters;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.UserParametersDAO;

public class UserParametersDAOJDBCImpl implements UserParametersDAO {

	private final static String SELECT_BY_ID = "SELECT user_id, login_parameters FROM USER_PARAMETERS WHERE user_id = ?;";
	private final static String INSERT = "INSERT INTO USER_PARAMETERS (login_parameters, user_id) VALUES (?,?);";
	private final static String UPDATE = "UPDATE USER_PARAMETERS SET login_parameters=? WHERE user_id=?;";
	private final static String DELETE = "DELETE FROM USER_PARAMETERS WHERE user_id = ?;";

	@Override
	public void createElement(UserParameters element) throws BusinessException {
		if (element == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(INSERT);
			pstmt.setString(1, element.getLoginParameters());
			pstmt.setInt(2, element.getUserId());
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
	public UserParameters selectElementById(int id) throws BusinessException {

		UserParameters params = null;

		if (Objects.isNull(id) || id < 0) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;

				pstmt = cnx.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					params = new UserParameters(rs.getString("login_parameters"), rs.getInt("user_id"));
				}
				pstmt.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET_ECHEC);
				throw businessException;
			}
		}
		return params;
	}

	@Override
	public UserParameters selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
		throw businessException;
	}

	@Override
	public void updateElement(UserParameters element) throws BusinessException {
		if (element == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, element.getLoginParameters());
			pstmt.setInt(2, element.getUserId());
			pstmt.executeUpdate();
			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);
			throw businessException;
		}

	}

	@Override
	public void deleteElementById(int id) throws BusinessException {

		if (Objects.isNull(id) || id < 0) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;

				pstmt = cnx.prepareStatement(DELETE);
				pstmt.setInt(1, id);
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
	public List<UserParameters> getAllElements() throws BusinessException {
		BusinessException businessException = new BusinessException();
		businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
		throw businessException;
	}

}
