package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.CryptoDAO;

public class CryptoDAOJDBCImpl implements CryptoDAO  {

	private static final String SELECT_MASTER_KEY = "SELECT masterKey FROM CRYPTO WHERE id = 1;";

	@Override
	public byte[] getMasterKey() throws BusinessException {

		byte[] masterKey = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(SELECT_MASTER_KEY);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				masterKey = rs.getString("masterKey").getBytes();
			}

			pstmt.close();
			cnx.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET_ECHEC);
			throw businessException;
		}
		return masterKey;
	}
	
}
