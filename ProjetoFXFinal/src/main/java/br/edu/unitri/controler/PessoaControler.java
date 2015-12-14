/**
 * 
 */
package br.edu.unitri.controler;

import java.io.Serializable;
import java.util.List;

import br.edu.unitri.interfaces.CrudRest;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.rest.ClientREST;

/**
 * @author marcos.fernando
 *
 */
public class PessoaControler extends ClientREST<Pessoa> implements CrudRest<Pessoa, Integer>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaControler() {
		super();
		setTargetClient("pessoa");
	}

	@Override
	public void insert(Pessoa t) {
		try {
			salvar(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteByEntity(Pessoa t) {
		try {
			excluir(t);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Pessoa> listAll() {
		try {
			return listarAll(new Pessoa());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Pessoa> listByEntity(Pessoa t) {
		try {
			return buscarPorExemplo(t);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Pessoa listByEntyti(Pessoa t) {
		try {
			List<Pessoa> pessoas = listarAll(t);
			if (pessoas.size() > 0) {
				return pessoas.get(0);				
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
