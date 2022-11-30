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
	
	/**
	 * Echec de récupération de l'utilisateur
	 */
	public static final int REQUESTED_USER_IS_NULL=20002;
	
	/**
	 * Erreur de cryptage du mot de passe
	 */
	public static final int CRYPT_ERROR=20003;
	
	/**
	 * Erreur de décryptage du mot de passe
	 */
	public static final int DECRYPT_ERROR=20004;
	
	
	
	// -> Creation Compte 20100
	/**
	 * Le pseudo existe déjà
	 */
	public static final int PSEUDO_ALREADY_EXISTS=20100;
	
	/**
	 * L'EMAIL existe déjà
	 */
	public static final int EMAIL_ALREADY_EXISTS=20101;

	
	// --> Encheres 20200
	/**
	 * Valeur nulle dans les paramètres de fonction
	 */
	public static final int NULL_OR_BLANK_PARAMETER=20200;
}
