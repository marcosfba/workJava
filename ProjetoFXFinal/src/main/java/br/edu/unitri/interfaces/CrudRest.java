/**
 * 
 */
package br.edu.unitri.interfaces;

import java.util.List;

/**
 * @author marcos.fernando
 *
 */
public interface CrudRest<T, I> {
	
	void insert(T t);

	boolean deleteByEntity(T t);
	
	T listByEntyti(T t);

	List<T> listAll();
	
	List<T> listByEntity(T t);

}
