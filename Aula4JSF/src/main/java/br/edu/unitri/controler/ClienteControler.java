/**
 * 
 */
package br.edu.unitri.controler;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.unitri.interfaces.CRUD;
import br.edu.unitri.model.Cliente;
import br.edu.unitri.util.JpaUtil;


/**
 * @author marcos.fernando
 *
 */
public class ClienteControler implements CRUD<Cliente,Integer> {

	private EntityManager manager = JpaUtil.getManager();

	@Override
	public Cliente save(Cliente t) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		return t;
	}	

	@Override
	public boolean delete(Cliente t) throws SQLException {
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		return true;
	}

	@Override
	public boolean update(Cliente t) throws SQLException {
		boolean ok = false;
		Cliente cliente = manager.find(Cliente.class, t.getId());
		if (cliente != null) {
			ok = true;
			cliente.setCpf(t.getCpf());
			cliente.setDtNascimento(t.getDtNascimento());
			cliente.setEmail(t.getEmail());
			cliente.setIdentidade(t.getIdentidade());
			cliente.setNome(t.getNome());

			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
		}
		return ok;
	}

	@Override
	public Cliente getById(Integer i) throws SQLException {
		return manager.find(Cliente.class, (long) i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll() throws SQLException {
		return  manager.createQuery("from Cliente").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findAll(String qry, String parametros)
			throws SQLException {
		if (!parametros.isEmpty()) {
			qry = qry.concat(parametros);
		}
		return manager.createNativeQuery(qry ,Cliente.class).getResultList();
	}
	

}
