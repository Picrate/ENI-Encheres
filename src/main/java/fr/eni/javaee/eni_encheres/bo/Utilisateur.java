
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;

/**
 * Classe d'objet représentant un utilisateur
 * @author patrice
 *
 */

public class Utilisateur implements Serializable{

	private static final long serialVersionUID = 1L;
	private int no_utilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String password;
	private int credit;
	private boolean administrateur;
	
	
	public Utilisateur() {
		
	}
	
	/**
	 * Constructeur sans paramètres optionnels ni id.
	 * L'Utilsateur n'est pas administrateur, ne possède aucun crédit et n'a pas de téléphone.
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param password
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String password) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.credit = 0;
		this.telephone = null;
		this.administrateur = false;
	}
	
	/**
	 * Constructeur complet
	 * @param no_utilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param password
	 * @param credit
	 * @param administrateur
	 */
	public Utilisateur(int no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String password, int credit, boolean administrateur) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public int getNo_utilisateur() {
		return no_utilisateur;
	}


	public String getPseudo() {
		return pseudo;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getEmail() {
		return email;
	}


	public String getTelephone() {
		return telephone;
	}


	public String getPassword() {
		return password;
	}

	public int getCredit() {
		return credit;
	}


	/**
	 * Check du statut d'administrateur de l'utilsateur.
	 * @return true si administrateur  / false sinon
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}

	/*
	 * Setter de l'identificateur unique de l'utilsateur.
	 */
	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}


	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * Modification du nombre de crédit disponible pour l'utilsateur
	 * @param credit le nouveau nombre de crédit disponible.
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}


	/**
	 * Attribut du role administrateur
	 * true si admin / false sinon
	 * @param administrateur l'attribut à definir si l'utilsateur est administrateur.
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}


	@Override
	public String toString() {
		return "Utilisateur - [ no_utilisateur: "+this.no_utilisateur+",pseudo: "+this.pseudo+",nom: "+this.nom+",prenom: "+this.prenom+", email: "+this.email+",telephone: "+this.telephone+",rue: "+",code_postal: "+",ville: "+",password: "+this.password+",credit: "+this.credit+",administrateur:"+this.administrateur+"]";
	}
	
	
	
	
	
	
	
}
