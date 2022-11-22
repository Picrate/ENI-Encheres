package fr.eni.javaee.eni_encheres.dal;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.Utilisateur;

public interface UtilisateurDAO extends DAO<Utilisateur> {

	public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException;
	
	public Utilisateur getUtilisateurByMail(String email) throws BusinessException;
	
}
