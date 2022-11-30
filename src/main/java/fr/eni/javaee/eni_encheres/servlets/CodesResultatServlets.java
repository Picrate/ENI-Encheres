package fr.eni.javaee.eni_encheres.servlets;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesResultatServlets {
	
	// -> Erreurs Génériques Servlets : 30000
	/**
	 * Exception non gérée.
	 */
	public static final int UNHANDLED_EXCEPTION=30000;
	
	// -> Connexion : 30100
	/**
	 * Utilisateur ou mot de passe invalide
	 */
	public static final int USERNAME_OR_PASSWORD_INVALID=30100;
	
	
	// -> Creation Compte :30200
	/**
	 * L'email existe déjà
	 */
	public static final int EMAIL_ALREADY_EXISTS=30201;
	
	/**
	 * Le pseudo existe déjà
	 */
	public static final int PSEUDO_ALREADY_EXISTS=30202;
	
	/**
	 * Le pseudo existe déjà
	 */
	public static final int PASSWORD_MISMATCH=30203;
	
	/**
	 * Le pseudo existe déjà
	 */
	public static final int DELETE_USER_ERROR=30204;
	
	// --> Encheres
	/**
	 * Le montant proposé est inférieur à la la dernière enchère
	 */
	public static final int OFFER_TOO_LOW=30301;
	/*
	 * Pas assez de crédit
	 */
	public static final int NOT_ENOUGH_CREDITS=30302;
}
