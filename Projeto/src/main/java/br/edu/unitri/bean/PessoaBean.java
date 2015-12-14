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

import br.edu.unitri.ejb.PessoaDao;
import br.edu.unitri.interfaces.CrudBean;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.util.UtilBeanFaces;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class PessoaBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaSel;
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	@Inject
	private PessoaDao pessoaDao;

	public PessoaBean() {
		super();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoaSel() {
		return pessoaSel;
	}

	public void setPessoaSel(Pessoa pessoaSel) {
		this.pessoaSel = pessoaSel;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void init() {
		pessoa = new Pessoa();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (pessoa.getId() != null) {
			pessoaDao.update(pessoa);
		} else {
			pessoaDao.insert(pessoa);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void excluir() {
		if (pessoaSel != null) {
			if (pessoaDao.deleteByEntity(pessoaSel)) {
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada. Existem outras relações com a base de dados!",
						null, "WARNING", null);
			}
		}
	}

	@Override
	public void editar() {
		setPessoa(getPessoaSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void buscar() {
		pessoas.clear();
		pessoas = pessoaDao.listByEntity(pessoa);
		if (pessoas.size() == 0) {
			UtilBeanFaces.addMessage("Não existe pessoas para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void listarTodos() {
		pessoas = pessoaDao.listAll();
	}

}
