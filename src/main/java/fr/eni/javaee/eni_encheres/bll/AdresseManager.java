/**
 * 
 */
package fr.eni.javaee.eni_encheres.bll;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Adresse;
import fr.eni.javaee.eni_encheres.dal.DAO;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;

/**
 * @author patrice
 *
 */
public class AdresseManager {
	
	private AdresseManager instance = null;
	private DAO<Adresse> adresseDAO;
	
	private AdresseManager () {
		adresseDAO = DAOFactory.getAdresseDAO();
	}
	
	/**
	 * 
	 * Récupération de l'instance du singleton
	 * 
	 * @return le singleton
	 */
	public AdresseManager getInstance() {
		if(instance == null) {
			instance = new AdresseManager();
		}
		return instance;
	}
	
	public Adresse getAdresseById(int id) throws BusinessException {
		return this.adresseDAO.selectElementById(id);
	}

}
