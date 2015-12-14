/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.unitri.interfaces.CRUD;
import br.edu.unitri.model.Login;
import br.edu.unitri.util.JpaUtil;

/**
 * @author marcos.fernando
 *
 */
public class LoginControler implements CRUD<Login,Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager = JpaUtil.getManager();

	@Override
	public Login save(Login t) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		return t;
	}

	@Override
	public boolean delete(Login t) throws SQLException {
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		return true;
	}

	@Override
	public boolean update(Login t) throws SQLException {
		boolean ok = false;
		Login login = manager.find(Login.class, t.getId());
		if (login != null) {
			ok = true;
			login.setCpf(t.getCpf());
			login.setNome(t.getNome());
			login.setPassowrd(t.getPassowrd());
			login.setUserName(t.getUserName());

			manager.getTransaction().begin();
			manager.persist(login);
			manager.getTransaction().commit();
		}
		return ok;
	}

	@Override
	public Login getById(Integer i) throws SQLException {
		return manager.find(Login.class, (long) i);	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Login> findAll() throws SQLException {
		return  manager.createQuery("from Login").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Login> findAll(String qry, String parametros)
			throws SQLException {
		if (!parametros.isEmpty()) {
			qry = qry.concat(parametros);
		}
		return manager.createNativeQuery(qry ,Login.class).getResultList();
	}

}
