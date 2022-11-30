package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{


	public void deleteEnchereByUtilisateurId(int userId) throws BusinessException;

}
