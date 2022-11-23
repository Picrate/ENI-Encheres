/**
 * 
 */
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;

/**
 * @author Orel
 *
 */
public class Adresse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// on mettrait pas le numero de rue?
	private String rue;
	private int codePostal;
	private String ville;

	public Adresse() {
	}

	public Adresse(String rue, int codePostal, String ville) {
		this.rue = rue.toLowerCase();
		this.codePostal = codePostal;
		this.ville = ville.toUpperCase();
	}

	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}

	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Adresse [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
