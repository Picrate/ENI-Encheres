package fr.eni.javaee.eni_encheres.bll;

import fr.eni.javaee.eni_encheres.BusinessException;
import fr.eni.javaee.eni_encheres.bo.UserParameters;
import fr.eni.javaee.eni_encheres.dal.DAOFactory;
import fr.eni.javaee.eni_encheres.dal.UserParametersDAO;

public class UserParametersManager {

	private static UserParametersManager instance = null;
	private UserParametersDAO parametersDAO;
	
	private UserParametersManager() {
		this.parametersDAO = DAOFactory.getUserParametersDAO();
	}
	
	public static UserParametersManager getInstance() {
		if(instance == null) {
			instance = new UserParametersManager();
		}
		return instance;
	}
	
	public UserParameters getUserParametersByUserId(int userId) throws BusinessException {
		return this.parametersDAO.selectElementById(userId);
	}
	
	public void updateUserParameters(UserParameters parameters) throws BusinessException {
		this.parametersDAO.updateElement(parameters);
	}
	
	public void setUserParameters(UserParameters parameters) throws BusinessException {
		this.parametersDAO.createElement(parameters);
	}
	
	
	
	
}
