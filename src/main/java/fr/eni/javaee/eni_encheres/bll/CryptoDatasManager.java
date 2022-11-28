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

	 private String seed;
	 private String base64Params;
	 private byte[] masterKey;
	 
	 private static CryptoDatasManager instance = null;
	 
	 private CryptoDatasManager() throws BusinessException {
		 this.seed = DAOFactory.getCryptoDAO().getBase64Seed();
		 this.masterKey = DAOFactory.getCryptoDAO().getMasterKey();
		 this.base64Params = DAOFactory.getCryptoDAO().getBase64Params();
	 }
	 
	 public static CryptoDatasManager getInstance() throws BusinessException {
		 if(instance == null) {
			 instance = new CryptoDatasManager();
		 }
		 return instance;
	 }

	public String getSeed() {		
		return seed;
	}

	public byte[] getMasterKey() {
		return masterKey;
	}

	public String getbase64Params() {
		return base64Params;
	}

}
