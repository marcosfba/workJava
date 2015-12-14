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

import br.edu.unitri.interfaces.AbstractDao;
import br.edu.unitri.model.Email;
import br.edu.unitri.model.Pessoa;

/**
 * Session Bean implementation class EmailDao
 */
@Stateless
@LocalBean
public class EmailDao extends AbstractDao<Email, Integer>implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public Email getById(Integer i) {
		return manager.find(Email.class,(long)i);
	}

	@Override
	public void insert(Email email) {
		manager.persist(email);
	}

	@Override
	public Email update(Email email) {
		return manager.merge(email);
	}

	@Override
	public List<Email> listAll() {
		return manager.createQuery("select e from Email e",Email.class).getResultList();
	}

	public List<Email> listAllByPessoa(Pessoa pessoa) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Email> query = cb.createQuery(Email.class);
		Root<Email> root = query.from(Email.class);
		
		Predicate where1 = cb.equal(root.get("pessoa"),pessoa);
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (predicados.size() > 0) {
			query.where(cb.and(predicados.toArray(new Predicate[]{})));
		}
		return  manager.createQuery(query).getResultList();
	}

	@Override
	public List<Email> listByEntity(Email email) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Email> query = cb.createQuery(Email.class);
		Root<Email> root = query.from(Email.class);
		
		Predicate where1 = null, where2 = null, where3 = null, where4 = null;

		if (email.getId() != null) {
			where1 = cb.equal(root.get("id"), email.getId());
		}
		if (email.getDescricao() != null) {
			where2 = cb.equal(root.get("descricao"), email.getDescricao());
		}
		if (email.getTipoEmail() != null) {
			where3 = cb.equal(root.get("tipoEmail"), email.getTipoEmail());
		}		
		if (email.getPessoa() != null) {
			where4 = cb.equal(root.get("pessoa"), email.getPessoa());
		}		
		
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (where2 != null) { predicados.add(where2) ; }
		if (where3 != null) { predicados.add(where3) ; }
		if (where4 != null) { predicados.add(where4) ; }
		
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[]{})));
		}
		return  manager.createQuery(query).getResultList();
	}
	
	@Override
	public boolean delete(Integer i) {
		try {
			manager.remove(manager.find(Email.class,(long) i));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByEntity(Email email) {
		try {
			manager.remove(manager.merge(email));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
