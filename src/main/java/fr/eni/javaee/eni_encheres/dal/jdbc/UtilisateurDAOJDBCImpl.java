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
import fr.eni.javaee.eni_encheres.dal.UtilisateurDAO;


/**
 * @author patrice
 *
 */
public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {

	private static String SELECT_USER_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo LIKE ?;";
	private static String SELECT_USER_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email LIKE ?;";;
		
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
	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException {
		Utilisateur utilisateur = getUtilisateurByPseudoOrMail(pseudo, SELECT_USER_BY_PSEUDO);
		return utilisateur;
	}

	@Override
	public Utilisateur getUtilisateurByMail(String email) throws BusinessException {
		Utilisateur utilisateur = getUtilisateurByPseudoOrMail(email, SELECT_USER_BY_EMAIL);
		return utilisateur;		
	}
	
	/**
	 * Méthode de récupération de l'utilsateur par son pseudo ou son email
	 * @param pseudoOrEmail le pseudo ou l'email
	 * @param query la requete correspondante
	 * @return l'utilsateurte sélectionné.
	 * @throws BusinessException
	 */
	private Utilisateur getUtilisateurByPseudoOrMail(String pseudoOrEmail, String query) throws BusinessException {
		Utilisateur utilisateur = null;
				
		if(pseudoOrEmail == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.NULL_ATTRIBUTE_IN_QUERY);
			throw be;
		}
		else {
		
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(query);
				pstmt.setString(1,pseudoOrEmail);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next())
				{
					utilisateur = new Utilisateur(
							rs.getInt("no_utilisateur"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("mot_de_passe"),
							rs.getInt("credit"),
							rs.getBoolean("administrateur")
							);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJET_ECHEC);
					throw businessException;
			}
		}
			
		return utilisateur;
	}

}
