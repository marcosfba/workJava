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
import br.edu.unitri.model.Endereco;
import br.edu.unitri.model.Pessoa;

/**
 * Session Bean implementation class EnderecoDao
 */
@Stateless
@LocalBean
public class EnderecoDao extends AbstractDao<Endereco, Integer>implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public Endereco getById(Integer i) {
		return manager.find(Endereco.class,(long)i);
	}

	@Override
	public void insert(Endereco endereco) {
		manager.persist(endereco);
	}

	@Override
	public Endereco update(Endereco endereco) {
		return manager.merge(endereco);
	}

	@Override
	public List<Endereco> listAll() {
		return manager.createQuery("select e from Endereco e",Endereco.class).getResultList();
	}
	
	public List<Endereco> listAllByPessoa(Pessoa pessoa) {
		
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> query = cb.createQuery(Endereco.class);
		Root<Endereco> root = query.from(Endereco.class);
		
		Predicate where1 = cb.equal(root.get("pessoa"),pessoa);
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (predicados.size() > 0) {
			query.where(cb.and(predicados.toArray(new Predicate[]{})));
		}
		return  manager.createQuery(query).getResultList();
	}


	@Override
	public List<Endereco> listByEntity(Endereco endereco) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Endereco> query = cb.createQuery(Endereco.class);
		Root<Endereco> root = query.from(Endereco.class);
		EntityType<Endereco> typeEndereco = manager.getMetamodel().entity(Endereco.class);
		
		Predicate where1 = null, where2 = null, where3 = null, where4 = null, where5 = null, where6 = null, where7 = null;

		if (endereco.getId() != null) {
			where1 = cb.equal(root.get("id"), endereco.getId());
		}
		if (endereco.getEstado() != null) {
			where2 = cb.equal(root.get("estado"), endereco.getEstado());
		}
		if (endereco.getCep() != null) {
			where3 = cb.equal(root.get("cep"), endereco.getCep());
		}
		if (endereco.getTipoEndereco() != null) {
			where4 = cb.equal(root.get("tipoEndereco"), endereco.getTipoEndereco());
		}		
		if (endereco.getLogradouro() != null) {
			where5 =  cb.like(root.get(typeEndereco.getDeclaredSingularAttribute("logradouro", String.class)),
					endereco.getLogradouro());
		}
		if (endereco.getCidade() != null) {
			where6 =  cb.like(root.get(typeEndereco.getDeclaredSingularAttribute("cidade", String.class)),
					endereco.getLogradouro());
		}
		if (endereco.getPessoa() != null) {
			where7 = cb.equal(root.get("pessoa"), endereco.getPessoa());
		}
		
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (where2 != null) { predicados.add(where2) ; }
		if (where3 != null) { predicados.add(where3) ; }
		if (where4 != null) { predicados.add(where4) ; }
		if (where5 != null) { predicados.add(where5) ; }
		if (where6 != null) { predicados.add(where6) ; }
		if (where7 != null) { predicados.add(where7) ; }
		
		if (predicados.size() > 0) {
			query.where(cb.or(predicados.toArray(new Predicate[]{})));
		}
		return  manager.createQuery(query).getResultList();
	}
	
	@Override
	public boolean delete(Integer i) {
		try {
			manager.remove(manager.find(Endereco.class,(long) i));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByEntity(Endereco endereco) {
		try {
			manager.remove(manager.merge(endereco));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
