package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;

public interface CryptoDAO {

	public byte[] getMasterKey() throws BusinessException;
	
	public String getBase64Seed() throws BusinessException;
	
	public String getBase64Params() throws BusinessException;
	
}
