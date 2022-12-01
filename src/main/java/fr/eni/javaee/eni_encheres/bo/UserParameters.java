package fr.eni.javaee.eni_encheres.bo;

import java.io.Serializable;

/**
 * Pojo représentant les parametres d'un utilsateur
 * 
 * @author patrice
 *
 */
public class UserParameters implements Serializable {

	private static final long serialVersionUID = 1L;
	public int id;
	public String loginParameters;
	public int userId;

	public UserParameters() {

	}

	
	
	/**
	 * @param loginParameters
	 * @param userId
	 */
	public UserParameters(String loginParameters, int userId) {
		this.loginParameters = loginParameters;
		this.userId = userId;
	}



	/**
	 * @param id
	 * @param loginParameters
	 * @param userId
	 */
	public UserParameters(int id, String loginParameters, int userId) {
		this.id = id;
		this.loginParameters = loginParameters;
		this.userId = userId;
	}

	/**
	 * @param id
	 * @param loginParameters
	 */
	public UserParameters(int id, String loginParameters) {
		this.id = id;
		this.loginParameters = loginParameters;
	}

	/**
	 * @param loginParameters
	 */
	public UserParameters(String loginParameters) {
		this.loginParameters = loginParameters;
	}

	public int getId() {
		return id;
	}

	public String getLoginParameters() {
		return loginParameters;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLoginParameters(String loginParameters) {
		this.loginParameters = loginParameters;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserParameter [id=" + id + ", loginParameters=" + loginParameters + "]";
	}

	
}
