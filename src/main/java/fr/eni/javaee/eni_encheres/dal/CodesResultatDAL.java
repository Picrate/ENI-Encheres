package fr.eni.javaee.eni_encheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de l'insertion d'un avis à cause de la note
	 */
	public static final int INSERT_AVIS_NOTE_ECHEC=10002;
	
	/**
	 * Attribut de selection fournit à la méthode est NULL
	 */
	public static final int NULL_ATTRIBUTE_IN_QUERY=10003;
	
	/**
	 * Attribut de selection fournit à la méthode est NULL
	 */
	public static final int NULL_VALUE_IN_QUERY=10004;
	
	/**
	 * Echec général quand erreur non gérée à la selection 
	 */
	public static final int SELECT_OBJET_ECHEC=10005;
	
	/**
	 * Echec général quand erreur non gérée à la mise à jour des informations
	 */
	public static final int UPDATE_OBJET_ECHEC=10006;
	
	/**
	 * Echec général quand erreur non gérée à la mise à jour des informations
	 */
	public static final int DELETE_OBJET_ECHEC=10007;
	
	/**
	 * REquete non implémentée
	 */
	public static final int UNIMPLEMENTED_REQUEST=10008;
	
	
}
