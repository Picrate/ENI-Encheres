/**
 * 
 */
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Orel
 *
 */
public class Article implements Serializable, Comparable<Article> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int noArticle;
	String nomArticle;
	String description;
	LocalDateTime dateDebutEncheres;
	LocalDateTime dateFinEncheres;
	int miseAPrix;
	int prixVente;
	int noUtilisateur;
	int noCategorie;
	Boolean etatVente;
	Boolean etatRetrait;
	

	public Article() {
	}

	/**
	 * @param noArticle
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param etatVente
	 * @param etatRetrait
	 * @param utilisateur
	 * @param lieuRetrait
	 */
	public Article(int noArticle, String nomArticle, String description, Timestamp dateDebutEncheres,
			Timestamp dateFinEncheres, int miseAPrix, int prixVente, Boolean etatVente, Boolean etatRetrait,
			int utilisateurId) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.setDateDebutEncheres(dateDebutEncheres);
		this.setDateFinEncheres(dateFinEncheres);
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.etatRetrait = etatRetrait;
		this.noUtilisateur = utilisateurId;
	}
	
	
	
	

	/**
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param miseAPrix
	 * @param prixVente
	 * @param noUtilisateur
	 * @param noCategorie
	 */
	public Article(int noArticle, String nomArticle, String description, Timestamp dateDebutEncheres, Timestamp dateFinEncheres, int miseAPrix,
			int prixVente, int noUtilisateur, int noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.setDateDebutEncheres(dateDebutEncheres);
		this.setDateFinEncheres(dateFinEncheres);
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noUtilisateur = noUtilisateur;
		this.noCategorie = noCategorie;
	}

	/**
	 * @return the noArticle
	 */
	public int getNoArticle() {
		return noArticle;
	}

	/**
	 * @param noArticle the noArticle to set
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * @return the nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}

	/**
	 * @param nomArticle the nomArticle to set
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the dateDebutEncheres
	 */
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	/**
	 * @param dateDebutEncheres the dateDebutEncheres to set
	 */
	public void setDateDebutEncheres(Timestamp dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres.toLocalDateTime();
	}

	/**
	 * @return the dateFinEncheres
	 */
	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	/**
	 * @param dateFinEncheres the dateFinEncheres to set
	 */
	public void setDateFinEncheres(Timestamp dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres.toLocalDateTime();
	}

	/**
	 * @return the miseAPrix
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}

	/**
	 * @param miseAPrix the miseAPrix to set
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * @return the prixVente
	 */
	public int getPrixVente() {
		return prixVente;
	}

	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * @return the etatVente
	 */
	public Boolean getEtatVente() {
		return etatVente;
	}

	/**
	 * @param etatVente the etatVente to set
	 */
	public void setEtatVente(Boolean etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * @return the etatRetrait
	 */
	public Boolean getEtatRetrait() {
		return etatRetrait;
	}

	/**
	 * @param etatRetrait the etatRetrait to set
	 */
	public void setEtatRetrait(Boolean etatRetrait) {
		this.etatRetrait = etatRetrait;
	}

	/**
	 * @return the utilisateur
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setNoUtilisateur(int utilisateurId) {
		this.noUtilisateur = utilisateurId;
	}
	

	/**
	 * @return the noCategorie
	 */
	public int getNoCategorie() {
		return this.noCategorie;
	}

	/**
	 * @param noCategorie the noCategorie to set
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	@Override
	public String toString() {
		return "Article [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", etatRetrait=" + etatRetrait
				+ ", utilisateur=" + noUtilisateur +"]";
	} 

	@Override
	public int compareTo(Article o) {
		int value = -2;
		if (this.noArticle < o.getNoArticle()) {
			value = -1;
		} else if (this.noArticle == o.getNoArticle() ) {
			value = 0;
		} else {
			value = 1;
		}
		//System.out.println(value + " - " + this.noArticle + " - " + o.getNoArticle());
		return value;
	}

}
