/**
 * 
 */
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Orel
 *
 */
public class Enchere implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LocalDateTime dateEnchere;
	int montantEnchere;
	int no_utilisateur;
	int no_article;

	/**
	 * 
	 */
	public Enchere() {
	}

	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(int no_utilisateur, int no_article, LocalDateTime dateEnchere, int montantEnchere) {
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	
	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(int no_utilisateur, int no_article, int montantEnchere) {
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.dateEnchere = LocalDateTime.now();
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the dateEnchere
	 */
	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montantEnchere
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * @param montantEnchere the montantEnchere to set
	 */
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

			

	public int getNo_utilisateur() {
		return no_utilisateur;
	}

	public int getNo_article() {
		return no_article;
	}

	public void setNo_utilisateur(int no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public void setNo_article(int no_article) {
		this.no_article = no_article;
	}

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", no_utilisateur="
				+ no_utilisateur + ", no_article=" + no_article + "]";
	}



}
