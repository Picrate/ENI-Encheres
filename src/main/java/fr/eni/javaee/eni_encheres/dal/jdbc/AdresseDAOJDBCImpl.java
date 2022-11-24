package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Adresse;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.DAO;

public class AdresseDAOJDBCImpl implements DAO<Adresse> {

	private static final String INSERT_ADRESSE = "INSERT INTO ADRESSES (nom, code_postal, ville)  VALUES (?,?,?);";
	private static final String SELECT_ADRESSE_BY = "SELECT id, rue, code_postal, ville FROM ADRESSES WHERE ? = ?;";
	private static final String UPDATE_ADRESSE = "UPDATE ADRESSES SET id=?, rue=?, code_postal=?, ville=? WHERE id = ?;";
	

	@Override
	public void createElement(Adresse element) throws BusinessException {
		// Check si les valeurs entrées en parametre sont null;
		if (element == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;

				pstmt = cnx.prepareStatement(INSERT_ADRESSE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, element.getRue());
				pstmt.setInt(2, element.getCodePostal());
				pstmt.setString(3, element.getVille());
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					element.setId(rs.getInt(1));
				}
				pstmt.close();
				cnx.close();

			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
				throw businessException;
			}
		}

	}

	@Override
	public Adresse selectElementById(int id) throws BusinessException {
		return selectElementBy("id", String.valueOf(id));
	}

	
	/**
	 * "SELECT id, rue, code_postal, ville FROM ADRESSES WHERE ? = ?;";
	 */
	@Override
	public Adresse selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		Adresse adresse = null;
		// Check si les valeurs entrées en parametre sont null;
		if (nomAttribut == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else if (valeurAttribut == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_VALUE_IN_QUERY);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;

				/*
				 * On différencie les types de données de la valeur en fonction du nom de
				 * l'attribut On configure le PreparedStatement en fonction
				 */
				switch (nomAttribut) {
				case "id":
					pstmt = cnx.prepareStatement(SELECT_ADRESSE_BY);
					pstmt.setString(1, nomAttribut);
					pstmt.setString(2, valeurAttribut);
					break;
				default:
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
					throw businessException;
				}

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					adresse = new Adresse(rs.getInt("id"), rs.getString("rue"),
							rs.getInt("code_postal"), rs.getString("ville"));
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
		return adresse;
	}

	@Override
	public void updateElement(Adresse element) throws BusinessException {
		
				// Check si les valeurs entrées en parametre sont null;
				if (element == null) {
					BusinessException be = new BusinessException();
					be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
					throw be;
				} else {

					try (Connection cnx = ConnectionProvider.getConnection()) {

						PreparedStatement pstmt = null;

						pstmt = cnx.prepareStatement(UPDATE_ADRESSE);
						pstmt.setString(1, element.getRue());
						pstmt.setInt(2, element.getCodePostal());
						pstmt.setString(3, element.getVille());
						pstmt.setInt(4, element.getId());
						
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
	}

	@Override
	public void deleteElementById(int id) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Adresse> getAllElements() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
