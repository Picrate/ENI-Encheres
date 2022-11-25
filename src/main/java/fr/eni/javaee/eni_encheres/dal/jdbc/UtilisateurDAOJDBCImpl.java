/**
 * Implémentation JDBC de la DAO Utilisateur
 */
package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.DAO;
import fr.eni.javaee.eni_encheres.dal.UtilisateurDAO;

/**
 * @author patrice
 *
 */
public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {

	private static final String SELECT_USER_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = ?;";
	private static final String SELECT_USER_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo LIKE ?;";
	private static final String SELECT_USER_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email LIKE ?;";
	private static final String INSERT = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur ) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SELECT_ADRESSE_ID_BY_USER_ID = "SELECT adresse_id FROM UTILISATEURS WHERE no_utilisateur = ?;";

	/*
	 * INSERT = "INSERT INTO UTILISATEURS ( pseudo, nom, prenom, email, telephone,
	 * rue, code_postal, ville, mot_de_passe, credit, administrateur ) VALUES
	 * (?,?,?,?,?,?,?,?,?,?,?);"
	 */
	@Override
	public void createElement(Utilisateur element) throws BusinessException {

		if (element == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
		}

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = null;
			pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, element.getPseudo());
			pstmt.setString(2, element.getNom());
			pstmt.setString(3, element.getPrenom());
			pstmt.setString(4, element.getEmail());
			pstmt.setString(5, element.getTelephone());
			pstmt.setString(6, element.getAdresse().getRue());
			pstmt.setInt(7, element.getAdresse().getCodePostal());
			pstmt.setString(8, element.getAdresse().getVille());
			pstmt.setString(9, element.getPassword());
			pstmt.setInt(10, element.getCredit());
			pstmt.setBoolean(11, element.isAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				element.setNo_utilisateur(rs.getInt(1));
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

	@Override
	public Utilisateur selectElementById(int id) throws BusinessException {
		return selectElementBy("id",String.valueOf(id));
	}

	@Override
	public void updateElement(Utilisateur element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElementById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Utilisateur> getAllElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectElementBy(String nomAttribut, String valeurAttribut) throws BusinessException {
		Utilisateur utilisateur = null;

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
				case "pseudo":
					pstmt = cnx.prepareStatement(SELECT_USER_BY_PSEUDO);
					pstmt.setString(1, valeurAttribut);
					break;
				case "email":
					pstmt = cnx.prepareStatement(SELECT_USER_BY_EMAIL);
					pstmt.setString(1, valeurAttribut);
					break;
				case "id":
					pstmt = cnx.prepareStatement(SELECT_USER_BY_ID);
					pstmt.setInt(1, Integer.valueOf(valeurAttribut));
					break;
				default:
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
					throw businessException;
				}

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
							rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),
							rs.getString("telephone"), rs.getString("mot_de_passe"), rs.getInt("credit"),
							rs.getBoolean("administrateur"));
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

		return utilisateur;
	}

	
	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException {
		return selectElementBy("pseudo", pseudo);
	}

	@Override
	public Utilisateur getUtilisateurByMail(String email) throws BusinessException {
		return selectElementBy("email", email);
	}

	@Override
	public int getUtilisateurAdresseId(int userId) throws BusinessException {
		int adresse_id = -1;

		// Check si les valeurs entrées en parametre sont null;
		if (Objects.isNull(userId)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		} else if (userId <= 0) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.QUERY_ATTRIBUTE_OUT_OF_BOUNDS);
			throw be;
		} else {

			try (Connection cnx = ConnectionProvider.getConnection()) {

				PreparedStatement pstmt = null;
				pstmt = cnx.prepareStatement(SELECT_ADRESSE_ID_BY_USER_ID);
				pstmt.setInt(1, userId);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					adresse_id = rs.getInt(1);
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

		return adresse_id;
	}

}
