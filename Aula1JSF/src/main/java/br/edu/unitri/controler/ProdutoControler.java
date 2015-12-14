/**
 * 
 */
package br.edu.unitri.controler;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.unitri.Util.JpaUtil;
import br.edu.unitri.model.Produto;


/**
 * @author marcos.fernando
 *
 */
public class ProdutoControler implements CRUD<Produto,Integer> {

	private EntityManager manager = JpaUtil.getManager();

	public Produto save(Produto t) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		return t;
	}	

	public boolean delete(Produto t) throws SQLException {
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		return true;
	}

	public boolean update(Produto t, Integer i) throws SQLException {
		boolean ok = false;
		Produto produto = manager.find(Produto.class, (long) i);
		if (produto != null) {
			ok = true;
			produto.setDescricao(t.getDescricao());
			produto.setCodigo(t.getCodigo());
			produto.setPrecoCusto(t.getPrecoCusto());
			produto.setPrecoVenda(t.getPrecoVenda());
			produto.setQuantidade(t.getQuantidade());

			manager.getTransaction().begin();
			manager.persist(produto);
			manager.getTransaction().commit();
		}
		return ok;
	}

	public Produto getById(Integer i) throws SQLException {
		return manager.find(Produto.class, (long) i);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findAll() throws SQLException {
		return  manager.createQuery("from Produto").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findAll(String qry, String parametros)
			throws SQLException {
		if (!parametros.isEmpty()) {
			qry = qry.concat(parametros);
		}
		return manager.createNativeQuery(qry ,Produto.class).getResultList();
	}
	

}
