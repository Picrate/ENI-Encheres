package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Enchere;

public interface EnchereDAO extends DAO<Enchere>{
	public void deleteEnchereByUtilisateurId(int userId) throws BusinessException;
	public Enchere bestEnchereForArticle(int idArticle) throws BusinessException;
	public Enchere getEnchere(int userId, int articleId) throws BusinessException;

}
