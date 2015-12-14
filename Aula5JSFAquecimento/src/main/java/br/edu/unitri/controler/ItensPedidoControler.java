/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.unitri.interfaces.CRUD;
import br.edu.unitri.model.ItensPedido;
import br.edu.unitri.model.Pedido;
import br.edu.unitri.model.Produto;
import br.edu.unitri.util.JpaUtil;


/**
 * @author marcos.fernando
 *
 */
public class ItensPedidoControler implements CRUD<ItensPedido,Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager = JpaUtil.getManager();

	@Override
	public ItensPedido save(ItensPedido t) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		return t;
	}	

	@Override
	public boolean delete(ItensPedido t) throws SQLException {
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		return true;
	}

	@Override
	public boolean update(ItensPedido t) throws SQLException {
		boolean ok = false;
		ItensPedido itensPedido = manager.find(ItensPedido.class, t.getIdItem());
		if (itensPedido != null) {
			ok = true;
			itensPedido.setProduto(t.getProduto());
			itensPedido.setPedido(t.getPedido());
			itensPedido.setQtdItem(t.getQtdItem());
			itensPedido.setVlrDesconto(t.getVlrDesconto());
			itensPedido.setVlrItem(t.getVlrItem());

			manager.getTransaction().begin();
			manager.persist(itensPedido);
			manager.getTransaction().commit();
		}
		return ok;
	}

	@Override
	public ItensPedido getById(Integer i) throws SQLException {
		return manager.find(ItensPedido.class, (long) i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItensPedido> findAll() throws SQLException {
		return  manager.createQuery("from ItensPedido").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ItensPedido> findAll(String qry, String parametros)
			throws SQLException {
		if (!parametros.isEmpty()) {
			qry = qry.concat(parametros);
		}
		return manager.createNativeQuery(qry ,ItensPedido.class).getResultList();
	}
	
	public void savePedLista(List<Produto> produtos, Pedido pedido) {
		boolean ok = true;
		for (Produto produto : produtos) {
			ItensPedido itens = new ItensPedido(pedido, produto,
					produto.getQuantidade(), produto.getPrecoVenda(),
					BigDecimal.ZERO);
			try {
				if (save(itens) == null) {
					ok = false;
				}
				if (!ok) {
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean removeItensByPedido(Pedido pedido) {
		Query query = manager.createQuery("delete from ItensPedido itp where itp.pedido_id = :p");
	    int deletedCount = query.setParameter("p", pedido.getId()).executeUpdate();
	    return deletedCount > 0;
	}
	

}
