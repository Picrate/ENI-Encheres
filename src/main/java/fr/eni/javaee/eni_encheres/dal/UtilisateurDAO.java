package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {

	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException;
	
	public Utilisateur getUtilisateurByMail(String email) throws BusinessException;
	
	/**
	 * Requete de récupération de l'adresse_id de l'utilisateur
	 * 
	 * @param userId l'identifiant de l'utilsateur pour lequel rechercher l'adresse
	 * @return l'identifiant de l'adresse
	 * @throws BusinessException
	 */
	public int getUtilisateurAdresseId(int userId) throws BusinessException;
	
}
