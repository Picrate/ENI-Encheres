package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;

public interface CryptoDAO {

	public byte[] getMasterKey() throws BusinessException;
}
