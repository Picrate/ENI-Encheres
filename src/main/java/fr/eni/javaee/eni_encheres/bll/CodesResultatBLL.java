package fr.eni.javaee.eni_encheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec quand l'identifiant est null
	 */
	public static final int PSEUDO_OR_EMAIL_NULL=20000;
	/**
	 * Echec quand le mot de passe est null
	 */
	public static final int PASSWORD_IS_NULL=20001;
	
	
}
