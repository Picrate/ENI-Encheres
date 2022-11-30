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
	
	private static AdresseManager instance = null;
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
	public static AdresseManager getInstance() {
		if(instance == null) {
			instance = new AdresseManager();
		}
		return instance;
	}
	
	public Adresse getAdresseById(int id) throws BusinessException {
		Adresse adresse = this.adresseDAO.selectElementById(id);
		return adresse;
	}
	
	public void createNewAdresse(Adresse adresse) throws BusinessException {
		this.adresseDAO.createElement(adresse);
	}
	
	public void deleteAdresse(Adresse adresse) throws BusinessException {
		this.adresseDAO.deleteElementById(adresse.getId());
	}
	
	public void updateAdresse(Adresse adresse) throws BusinessException {
		this.adresseDAO.updateElement(adresse);
	}

}
