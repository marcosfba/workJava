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

import br.edu.unitri.ejb.EmailDao;
import br.edu.unitri.ejb.EnderecoDao;
import br.edu.unitri.ejb.PessoaDao;
import br.edu.unitri.ejb.TelefoneDao;
import br.edu.unitri.interfaces.CrudBean;
import br.edu.unitri.model.Email;
import br.edu.unitri.model.Endereco;
import br.edu.unitri.model.Pessoa;
import br.edu.unitri.model.Telefone;
import br.edu.unitri.util.UtilBeanFaces;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class ContatoBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Pessoa contato = new Pessoa();
	private Endereco endereco = new Endereco();
	private Email email = new Email();
	private Telefone telefone = new Telefone();

	private Pessoa contatoSel;

	private List<Pessoa> contatos = new ArrayList<Pessoa>();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private List<Email> emails = new ArrayList<Email>();
	private List<Telefone> telefones = new ArrayList<Telefone>();

	@Inject
	private PessoaDao pessoaDao;
	@Inject
	private EnderecoDao enderecoDao;
	@Inject
	private EmailDao emailDao;
	@Inject
	private TelefoneDao telefoneDao;

	public ContatoBean() {
		super();
	}

	public Pessoa getContato() {
		return contato;
	}

	public void setContato(Pessoa contato) {
		this.contato = contato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Pessoa getContatoSel() {
		return contatoSel;
	}

	public void setContatoSel(Pessoa contatoSel) {
		this.contatoSel = contatoSel;
	}

	public List<Pessoa> getContatos() {
		return contatos;
	}

	public void setContatos(List<Pessoa> contatos) {
		this.contatos = contatos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	@PostConstruct
	public void init() {
		contato = new Pessoa();
		email = new Email();
		endereco = new Endereco();
		telefone = new Telefone();

		emails.clear();
		enderecos.clear();
		telefones.clear();
		listarTodos();
	}

	public void adicionarTelefone() {
		boolean ok = false;
		if (telefones.size() == 0) { ok = true; }
		else {
			ok = true;
			for (Telefone telefoneInt : telefones) {
				if (telefoneInt.getTipoTelefone().equals(telefone.getTipoTelefone())) {
					ok = false;
					break;
				}
			}
		}
		if (ok) {
			telefones.add(telefone);
			limparTelefone();
			UtilBeanFaces.addMessage("Telefone adicionado com sucesso!","mesTelefone","INFO", null);
		} else {
			UtilBeanFaces.addMessage("Não pode ser adicionado telefones do mesmo tipo","mesTelefone","ERRO", null);
		}
	}

	public void limparTelefone() {
		telefone = new Telefone();
	}

	public void adicionarEndereco() {
		boolean ok = false;
		if (enderecos.size() == 0) { ok = true; }
		else {
			ok = true;
			for (Endereco enderecoInt : enderecos) {
				if (enderecoInt.getTipoEndereco().equals(endereco.getTipoEndereco())) {
					ok = false;
					break;
				}
			}
		}
		if (ok) {
			enderecos.add(endereco);
			limparEndereco();
			UtilBeanFaces.addMessage("Endereço adicionado com sucesso!","mesEndereco","INFO", null);
		} else {
			UtilBeanFaces.addMessage("Não pode ser adicionado endereços do mesmo tipo","mesEndereco","ERRO", null);
		}
	}

	public void limparEndereco() {
		endereco = new Endereco();
	}


	public void adicionarEmail() {
		boolean ok = false;
		if (emails.size() == 0) { ok = true; }
		else {
			ok = true;
			for (Email emailInt : emails) {
				if (emailInt.getTipoEmail().equals(email.getTipoEmail())) {
					ok = false;
					break;
				}
			}
		}
		if (ok) {
			emails.add(email);
			limparEmail();
			UtilBeanFaces.addMessage("Email adicionado com sucesso!","mesContato","INFO", null);
		} else {
			UtilBeanFaces.addMessage("Não pode ser adicionado emails do mesmo tipo","mesContato","ERRO", null);
		}
	}

	public void limparEmail() {
		email = new Email();
	}

	private void salvarListas(Pessoa contato) {
		// salvar lista de endereços
		for (Endereco endereco : enderecos) {
			if (endereco.getPessoa() == null) {
				endereco.setPessoa(contato);
			}
			if (endereco.getId() != null) {
				enderecoDao.update(endereco);
			} else {
				enderecoDao.insert(endereco);
			}
		}
		// salvar lista de emails
		for (Email email : emails) {
			if (email.getPessoa() == null) {
				email.setPessoa(contato);
			}
			if (email.getId() != null) {
				emailDao.update(email);
			} else {
				emailDao.insert(email);
			}
		}
		// salvar lista de telefones
		for (Telefone telefone : telefones) {
			if (telefone.getPessoa() == null) {
				telefone.setPessoa(contato);
			}
			if (telefone.getId() != null) {
				telefoneDao.update(telefone);
			} else {
				telefoneDao.insert(telefone);
			}
		}
	}

	private void apagarListas(Pessoa contato) {
		popularListas(contato);
		for (Endereco endereco : enderecos) {
			enderecoDao.deleteByEntity(endereco);
		}
		for (Email email : emails) {
			emailDao.deleteByEntity(email);
		}
		for (Telefone telefone : telefones) {
			telefoneDao.deleteByEntity(telefone);
		}
	}

	@Override
	public void salvar() {

		if (contato.getId() != null) {
			pessoaDao.update(contato);
		} else {
			pessoaDao.insert(contato);
		}
		salvarListas(contato);
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", "mesPessoa", "INFO", null);
		init();
	}

	@Override
	public void buscar() {
		contatos.clear();
		contatos = pessoaDao.listByEntity(contato);
		if (contatos.size() == 0) {
			UtilBeanFaces.addMessage("Não existe contatos para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void excluir() {
		if (contatoSel != null) {
			apagarListas(contatoSel);
			if (pessoaDao.deleteByEntity(contatoSel)) {
				init();
				UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
			} else {
				UtilBeanFaces.addMessage("Operação não pode ser realizada. Existem outras relações com a base de dados!",
						null, "WARNING", null);
			}
		}
	}
	
	private void popularListas(Pessoa contato) {
		emails.clear();
		enderecos.clear();
		telefones.clear();
		emails.addAll(emailDao.listAllByPessoa(contato));
		telefones.addAll(telefoneDao.listAllByPessoa(contato));
		enderecos.addAll(enderecoDao.listAllByPessoa(contato));
	}
	
	public void buscarTelefone(String id) {
		telefones.clear();
		telefones.addAll(telefoneDao.listAllByPessoa(pessoaDao.getById(Integer.valueOf(id))));		
	}

	public void buscarEndereco(String id) {
		enderecos.clear();
		enderecos.addAll(enderecoDao.listAllByPessoa(pessoaDao.getById(Integer.valueOf(id))));		
	}
	
	public void buscarEmail(String id) {
		emails.clear();
		emails.addAll(emailDao.listAllByPessoa(pessoaDao.getById(Integer.valueOf(id))));		
	}

	@Override
	public void editar() {
		setContato(getContatoSel());
		popularListas(getContatoSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		contatos = pessoaDao.listAll();
	}

}
