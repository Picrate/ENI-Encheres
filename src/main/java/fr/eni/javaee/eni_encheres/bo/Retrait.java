/**
 * 
 */
package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;

/**
 * @author Orel
 *
 */
public class Retrait implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	Adresse adresseRetrait;
	/**
	 * 
	 */
	public Retrait() {
	}
	/**
	 * @param adresseRetrait
	 */
	public Retrait(Adresse adresseRetrait) {
		this.adresseRetrait = adresseRetrait;
	}
	/**
	 * @return the adresseRetrait
	 */
	public Adresse getAdresseRetrait() {
		return adresseRetrait;
	}
	/**
	 * @param adresseRetrait the adresseRetrait to set
	 */
	public void setAdresseRetrait(Adresse adresseRetrait) {
		this.adresseRetrait = adresseRetrait;
	}
	@Override
	public String toString() {
		return "Retrait [adresseRetrait=" + adresseRetrait + "]";
	}
	
}
