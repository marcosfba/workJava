package br.edu.unitri.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import br.edu.unitri.interfaces.AbstractDao;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.model.Telefone;

/**
 * Session Bean implementation class TelefoneDao
 */
@Stateless
@LocalBean
public class TelefoneDao extends AbstractDao<Telefone, Integer> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public Telefone getById(Integer i) {
		return manager.find(Telefone.class,(long)i);
	}

	@Override
	public void insert(Telefone telefone) {
		manager.persist(telefone);
	}

	@Override
	public Telefone update(Telefone telefone) {
		return manager.merge(telefone);
	}

	@Override
	public List<Telefone> listAll() {
		return manager.createQuery("select t from Telefone t",Telefone.class).getResultList();
	}
	
	public List<Telefone> listAllByPessoa(Pessoa pessoa) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Telefone> query = cb.createQuery(Telefone.class);
		Root<Telefone> root = query.from(Telefone.class);
		
		Predicate where1 = cb.equal(root.get("pessoa"),pessoa);
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (predicados.size() > 0) {
			query.where(cb.and(predicados.toArray(new Predicate[]{})));
		}
		return  manager.createQuery(query).getResultList();
	}


	@Override
	public List<Telefone> listByEntity(Telefone telefone) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Telefone> query = cb.createQuery(Telefone.class);
		Root<Telefone> root = query.from(Telefone.class);
		EntityType<Telefone> typeTelefone = manager.getMetamodel().entity(Telefone.class);
		
		Predicate where1 = null, where2 = null, where3 = null, where4 = null, where5 = null;

		if (telefone.getId() != null) {
			where1 = cb.equal(root.get("id"), telefone.getId());
		}
		if (telefone.getNumDdd() != null) {
			where2 = cb.equal(root.get("numDdd"), telefone.getNumDdd());
		}
		if (telefone.getTipoTelefone() != null) {
			where3 = cb.equal(root.get("tipoTelefone"), telefone.getTipoTelefone());
		}		
		if (telefone.getNumTelefone() != null) {
			where4 =  cb.like(root.get(typeTelefone.getDeclaredSingularAttribute("numTelefone", String.class)),
					telefone.getNumTelefone());
		}
		if (telefone.getPessoa() != null) {
			where5 = cb.equal(root.get("pessoa"), telefone.getPessoa());
		}		
		
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (where2 != null) { predicados.add(where2) ; }
		if (where3 != null) { predicados.add(where3) ; }
		if (where4 != null) { predicados.add(where4) ; }
		if (where5 != null) { predicados.add(where5) ; }
		
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[]{})));
		}
		return  manager.createQuery(query).getResultList();
	}
	
	@Override
	public boolean delete(Integer i) {
		try {
			manager.remove(manager.find(Telefone.class,(long) i));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByEntity(Telefone telefone) {
		try {
			manager.remove(manager.merge(telefone));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}