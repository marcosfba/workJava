package br.edu.unitri.interfaces;

import java.util.List;

public abstract class AbstractDao<T, I> {
	
	public abstract T getById(I i);

	public abstract void insert(T t);

	public abstract T update(T t);

	public abstract boolean delete(I i);
	
	public abstract boolean deleteByEntity(T t);

	public abstract List<T> listAll();
	
	public abstract List<T> listByEntity(T t);
}