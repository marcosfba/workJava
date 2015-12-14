/**
 * 
 */
package br.edu.unitri.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.unitri.ejb.EnderecoDao;
import br.edu.unitri.interfaces.CrudBean;
import br.edu.unitri.model.Endereco;
import br.edu.unitri.util.UtilBeanFaces;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class EnderecoBean implements CrudBean, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Endereco endereco = new Endereco();
	private Endereco enderecoSel;
	private List<Endereco> enderecos = new ArrayList<Endereco>();

	@Inject
	private EnderecoDao enderecoDao;
	
	public EnderecoBean() {
		super();
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEnderecoSel() {
		return enderecoSel;
	}

	public void setEnderecoSel(Endereco enderecoSel) {
		this.enderecoSel = enderecoSel;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	@PostConstruct
	public void init(){
		endereco = new Endereco();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (endereco.getId() != null) {
			enderecoDao.update(endereco);
		} else {
			enderecoDao.insert(endereco);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void excluir() {
		if (enderecoSel != null) {
			enderecoDao.deleteByEntity(enderecoSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void buscar() {
		enderecos.clear();
		enderecos = enderecoDao.listByEntity(endereco);
		if (enderecos.size() == 0) {
			UtilBeanFaces.addMessage("Não existe endereços para serem exibidos",null,"WARNING", null);
		}
	}

	@Override
	public void editar() {
		setEndereco(getEnderecoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		enderecos = enderecoDao.listAll();
	}

}
