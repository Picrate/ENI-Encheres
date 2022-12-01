/**
 * 
 */
package fr.eni.javaee.eni_encheres.bll;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;

/**
 * @author patrice
 *
 */
public class CryptoDatasManager {

	private byte[] masterKey;

	private static CryptoDatasManager instance = null;

	private CryptoDatasManager() throws BusinessException {
		this.masterKey = DAOFactory.getCryptoDAO().getMasterKey();
	}

	public static CryptoDatasManager getInstance() throws BusinessException {
		if (instance == null) {
			instance = new CryptoDatasManager();
		}
		return instance;
	}

	/**
	 * Récupère la masterKey
	 * @return
	 */
	public byte[] getMasterKey() {
		return masterKey;
	}

}
