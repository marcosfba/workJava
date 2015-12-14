/**
 * 
 */
package br.edu.unitri.dao;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author marcos.fernando
 *
 */
public class JpaFactory {
	
	@Produces
	@PersistenceContext
	private EntityManager manager;


}
