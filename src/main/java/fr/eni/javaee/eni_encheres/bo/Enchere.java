/**
 * 
 */
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Orel
 *
 */
public class Enchere implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LocalDate dateEnchere;
	int montantEnchere;

	/**
	 * 
	 */
	public Enchere() {
	}

	/**
	 * @param dateEnchere
	 * @param montantEnchere
	 */
	public Enchere(LocalDate dateEnchere, int montantEnchere) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	/**
	 * @return the dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param dateEnchere the dateEnchere to set
	 */
	public void setDateEnchere(LocalDate dateEnchere) {
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

	@Override
	public String toString() {
		return "Enchere [dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + "]";
	}

}
