/**
 * Implémentation JDBC de la DAO Utilisateur
 */
package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.CodesResultatDAL;
import fr.eni.javaee.eni_encheres.dal.ConnectionProvider;
import fr.eni.javaee.eni_encheres.dal.DAO;

/**
 * @author patrice
 *
 */
public class UtilisateurDAOJDBCImpl implements DAO<Utilisateur> {

	private static final String SELECT_USER_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo LIKE ?;";
	private static final String SELECT_USER_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email LIKE ?;";
	// TODO Voir si Constante  requête utile garder sinon supprimer
	// private static final String SELECT_USER_BY = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE ? LIKE ?;";

	@Override
	public void createElement(Utilisateur element) {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur selectElementById(int id) {
		// TODO Auto-generated method stub
		return null;
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
				
				PreparedStatement pstmt=null;
				
				/*
				 * On différencie les types de données de la valeur en fonction du nom de l'attribut
				 * On configure le PreparedStatement en fonction
				 */				
				switch (nomAttribut) {
				case "pseudo":
					pstmt = cnx.prepareStatement(SELECT_USER_BY_PSEUDO);
					break;
				case "email":
					pstmt = cnx.prepareStatement(SELECT_USER_BY_EMAIL);
					break;
				default:
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.UNIMPLEMENTED_REQUEST);
					throw businessException;
				}
				pstmt.setString(1, valeurAttribut);
				
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
}
