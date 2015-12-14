/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.unitri.interfaces.CRUD;
import br.edu.unitri.model.Pedido;
import br.edu.unitri.util.JpaUtil;


/**
 * @author marcos.fernando
 *
 */
public class PedidoControler implements CRUD<Pedido,Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager = JpaUtil.getManager();

	@Override
	public Pedido save(Pedido t) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		return t;
	}	

	@Override
	public boolean delete(Pedido t) throws SQLException {
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		return true;
	}

	@Override
	public boolean update(Pedido t) throws SQLException {
		boolean ok = false;
		Pedido pedido = manager.find(Pedido.class, t.getId());
		if (pedido != null) {
			ok = true;
			pedido.setDtPedido(t.getDtPedido());
			pedido.setCliente(t.getCliente());
			pedido.setObservacao(t.getObservacao());

			manager.getTransaction().begin();
			manager.persist(pedido);
			manager.getTransaction().commit();
		}
		return ok;
	}

	@Override
	public Pedido getById(Integer i) throws SQLException {
		return manager.find(Pedido.class, (long) i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAll() throws SQLException {
		return  manager.createQuery("from Pedido").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> findAll(String qry, String parametros)
			throws SQLException {
		if (!parametros.isEmpty()) {
			qry = qry.concat(parametros);
		}
		return manager.createNativeQuery(qry ,Pedido.class).getResultList();
	}
	

}
