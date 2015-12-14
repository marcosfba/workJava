/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.unitri.interfaces.CRUD;
import br.edu.unitri.model.Foto;
import br.edu.unitri.model.Produto;
import br.edu.unitri.util.JpaUtil;

/**
 * @author marcos.fernando
 *
 */
public class FotoControler implements CRUD<Foto, Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager manager = JpaUtil.getManager();

	@Override
	public Foto save(Foto t) throws SQLException {
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		return t;
	}

	@Override
	public boolean delete(Foto t) throws SQLException {
		manager.getTransaction().begin();
		manager.remove(t);
		manager.getTransaction().commit();
		return true;
	}

	@Override
	public boolean update(Foto t) throws SQLException {
		boolean ok = false;
		Foto foto = manager.find(Foto.class, t.getFotoId());
		if (foto != null) {
			ok = true;
			foto.setFotoDescricao(t.getFotoDescricao());
			foto.setFotoImagem(t.getFotoImagem());
			foto.setProduto(t.getProduto());

			manager.getTransaction().begin();
			manager.persist(foto);
			manager.getTransaction().commit();
		}
		return ok;
	}

	@Override
	public Foto getById(Integer i) throws SQLException {
		return manager.find(Foto.class, (long) i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> findAll() throws SQLException {
		return manager.createQuery("from Foto").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Foto> findAll(String qry, String parametros)
			throws SQLException {
		if (!parametros.isEmpty()) {
			qry = qry.concat(parametros);
		}
		return manager.createNativeQuery(qry, Foto.class).getResultList();
	}

	public List<Foto> listByProdutos(Produto produto) {
		CriteriaBuilder cb = JpaUtil.getManager().getCriteriaBuilder();
		CriteriaQuery<Foto> query = cb.createQuery(Foto.class);
		Root<Foto> root = query.from(Foto.class);
		Predicate where1 = cb.equal(root.get("produto"),produto);		
		query.where(where1);
		return manager.createQuery(query).getResultList();
	}
}
