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
import br.edu.unitri.model.Email;
import br.edu.unitri.model.Endereco;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.model.Telefone;

/**
 * Session Bean implementation class PessoaDao
 */
@Stateless
@LocalBean
public class PessoaDao extends AbstractDao<Pessoa, Integer>implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager manager;

	@Override
	public Pessoa getById(Integer i) {
		return manager.find(Pessoa.class,(long)i);
	}

	@Override
	public void insert(Pessoa pessoa) {
		manager.persist(pessoa);
	}

	@Override
	public Pessoa update(Pessoa pessoa) {
		return manager.merge(pessoa);
	}


	@Override
	public List<Pessoa> listAll() {
		return manager.createQuery("select p from Pessoa p",Pessoa.class).getResultList();
	}

	@Override
	public List<Pessoa> listByEntity(Pessoa pessoa) {

		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> query = cb.createQuery(Pessoa.class);
		Root<Pessoa> root = query.from(Pessoa.class);
		EntityType<Pessoa> typePessoa = manager.getMetamodel().entity(Pessoa.class);
		
		Predicate where1 = null, where2 = null, where3 = null, where4 = null;

		if (pessoa.getId() != null) {
			where1 = cb.equal(root.get("id"), pessoa.getId());
		}
		if (pessoa.getDtNascimento() != null) {
			where2 = cb.equal(root.get("dtNascimento"), pessoa.getDtNascimento());
		}
		if (pessoa.getTipoPessoa() != null) {
			where3 = cb.equal(root.get("tipoPessoa"), pessoa.getTipoPessoa());
		}		
		if (pessoa.getNome() != null) {
			where4 =  cb.equal(root.get(typePessoa.getDeclaredSingularAttribute("nome", String.class)),
					pessoa.getNome());
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
		if (inconsistencia(getById(i))) {
			manager.remove(manager.find(Pessoa.class,(long) i));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteByEntity(Pessoa pessoa) {
		if (inconsistencia(pessoa)) {
			manager.remove(manager.merge(pessoa));
			return true;
		} else {
			return false;
		}
	}
	
	private boolean inconsistencia(Pessoa pessoa) {
		if (manager.createQuery("select e from Email e where e.pessoa = :pessoa", Email.class).setParameter("pessoa", pessoa).getResultList().size() > 0 ) {
			return false;
		}
		if (manager.createQuery("select e from Endereco e where e.pessoa = :pessoa", Endereco.class).setParameter("pessoa", pessoa).getResultList().size() > 0 ) {
			return false;
		}
		if (manager.createQuery("select t from Telefone t where t.pessoa = :pessoa", Telefone.class).setParameter("pessoa", pessoa).getResultList().size() > 0 ) {
			return false;
		}
		return true;
	}
 
}
