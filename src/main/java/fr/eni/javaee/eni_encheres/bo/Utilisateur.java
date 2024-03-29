
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;
import java.util.Objects;

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
	private Adresse adresse;
	private int credit;
	private boolean administrateur;
	
	
	public Utilisateur() {
		
	}
	
	/**
	 * Constructeur sans paramètres optionnels
	 * L'Utilisateur n'est pas administrateur, ne possède aucun crédit.
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param password
	 * @param adresse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String password, Adresse adresse) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.credit = 0;
		this.telephone = telephone;
		this.administrateur = false;
		this.adresse = adresse;
	}
	
	/**
	 * Constructeur sans adresse pour les requetes de connexion
	 * @param no_utilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param password
	 * @param credit
	 * @param administrateur
	 * 
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
	 * @param adresse
	 */
	public Utilisateur(int no_utilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String password, int credit, boolean administrateur, Adresse adresse) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.credit = credit;
		this.administrateur = administrateur;
		this.adresse = adresse;
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


	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Utilisateur - [ no_utilisateur: "+this.no_utilisateur+",pseudo: "+this.pseudo+",nom: "+this.nom+",prenom: "+this.prenom+", email: "+this.email+",telephone: "+this.telephone+",password: "+this.password+",credit: "+this.credit+",administrateur:"+this.administrateur+" Adresse: "+ (Objects.isNull(adresse) ? "VIDE" :adresse.toString())+"]";
	}
	
	
	
	
	
	
	
}
