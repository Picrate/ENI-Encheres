/**
 * Implémentation JDBC de la DAO Utilisateur
 */
package fr.eni.javaee.eni_encheres.dal.jdbc;

import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;
import fr.eni.javaee.eni_encheres.dal.DAO;
import fr.eni.javaee.eni_encheres.dal.UtilisateurDAO;

/**
 * @author patrice
 *
 */
public class UtilisateurDAOJDBCImpl implements UtilisateurDAO {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUtilisateurByMail(String email) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
