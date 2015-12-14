/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.util.List;

import br.edu.unitri.interfaces.CrudRest;
import br.edu.unitri.model.Telefone;
import br.edu.unitri.rest.ClientREST;

/**
 * @author marcos.fernando
 *
 */
public class TelefoneControler extends ClientREST<Telefone> implements CrudRest<Telefone, Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TelefoneControler() {
		super();
		setTargetClient("telefone");
	}

	@Override
	public void insert(Telefone t) {
		try {
			salvar(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteByEntity(Telefone t) {
		try {
			excluir(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Telefone> listAll() {
		try {
			return listarAll(new Telefone());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Telefone> listByEntity(Telefone t) {
		try {
			return buscarPorExemplo(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Telefone listByEntyti(Telefone t) {
		try {
			return buscarPorExemplo(t).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
