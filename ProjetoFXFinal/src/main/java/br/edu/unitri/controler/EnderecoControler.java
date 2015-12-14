/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.util.List;

import br.edu.unitri.interfaces.CrudRest;
import br.edu.unitri.model.Endereco;
import br.edu.unitri.rest.ClientREST;

/**
 * @author marcos.fernando
 *
 */
public class EnderecoControler extends ClientREST<Endereco> implements CrudRest<Endereco, Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnderecoControler() {
		super();
		setTargetClient("endereco");
	}

	@Override
	public void insert(Endereco t) {
		try {
			salvar(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteByEntity(Endereco t) {
		try {
			excluir(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Endereco> listAll() {
		try {
			return listarAll(new Endereco());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Endereco> listByEntity(Endereco t) {
		try {
			return buscarPorExemplo(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Endereco listByEntyti(Endereco t) {
		try {
			return buscarPorExemplo(t).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
