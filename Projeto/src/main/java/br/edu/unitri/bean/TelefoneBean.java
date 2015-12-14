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

import br.edu.unitri.ejb.TelefoneDao;
import br.edu.unitri.interfaces.CrudBean;
import br.edu.unitri.model.Telefone;
import br.edu.unitri.util.UtilBeanFaces;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class TelefoneBean implements CrudBean, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Telefone telefone = new Telefone();
	private Telefone telefoneSel;
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@Inject
	private TelefoneDao telefoneDao;
	
	public TelefoneBean() {
		super();
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Telefone getTelefoneSel() {
		return telefoneSel;
	}

	public void setTelefoneSel(Telefone telefoneSel) {
		this.telefoneSel = telefoneSel;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@PostConstruct
	public void init(){
		telefone = new Telefone();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (telefone.getId() != null) {
			telefoneDao.update(telefone);
		} else {
			telefoneDao.insert(telefone);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void excluir() {
		if (telefoneSel != null) {
			telefoneDao.deleteByEntity(telefoneSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void buscar() {
		telefones.clear();
		telefones = telefoneDao.listByEntity(telefone);
		if (telefones.size() == 0) {
			UtilBeanFaces.addMessage("Não existe telefones para serem exibidos",null,"WARNING", null);
		}
	}

	@Override
	public void editar() {
		setTelefone(getTelefoneSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		telefones = telefoneDao.listAll();
	}

}
