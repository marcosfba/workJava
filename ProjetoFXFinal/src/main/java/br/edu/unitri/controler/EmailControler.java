/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.util.List;

import br.edu.unitri.interfaces.CrudRest;
import br.edu.unitri.model.Email;
import br.edu.unitri.rest.ClientREST;

/**
 * @author marcos.fernando
 *
 */
public class EmailControler extends ClientREST<Email> implements CrudRest<Email, Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailControler() {
		super();
		setTargetClient("email");
	}

	@Override
	public void insert(Email t) {
		try {
			salvar(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteByEntity(Email t) {
		try {
			excluir(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Email> listAll() {
		try {
			return listarAll(new Email());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Email> listByEntity(Email t) {
		try {
			return buscarPorExemplo(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Email listByEntyti(Email t) {
		try {
			return buscarPorExemplo(t).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
