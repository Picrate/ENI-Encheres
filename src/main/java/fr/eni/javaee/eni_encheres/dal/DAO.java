/**
 * Interface de definition des méthodes CRUD
 */
package fr.eni.javaee.eni_encheres.dal;

import java.util.List;

import fr.eni.javaee.eni_encheres.BusinessException;

/**
 * @author patrice
 *
 */
public interface DAO<T> {

	/**
	 * Création d'un nouvel élément dans la datasource.
	 * @param element l'élément à ajouter dans la datasource.
	 */
	public void createElement(T element)  throws BusinessException;
	
	/**
	 * Récupère un élément par son identifiant dans la datasource.
	 * @param id l'identifiant de l'élément à récupérer
	 * @return l'élément possédant l'identifiant.
	 */
	public T selectElementById(int id)  throws BusinessException;
	
	/**
	 * Met à jour un élément dans la datasource.
	 * @param element l'élément à ajouter.
	 */
	public void updateElement(T element)  throws BusinessException;
	
	/**
	 * Supprime un element dans la datasource par son identifiant
	 * @param id l'identifiant de l'élément à supprimer
	 */
	public void deleteElementById(int id)  throws BusinessException;
	
	/**
	 * Récupère la liste de tous les éléments contenus dans la datasource.
	 * @return tous les élémnts contenus dans la datasource.
	 */
	public List<T> getAllElements()  throws BusinessException;
	
}
