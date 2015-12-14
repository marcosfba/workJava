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
import br.edu.unitri.interfaces.CrudBean;
import br.edu.unitri.model.Email;
import br.edu.unitri.util.UtilBeanFaces;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class EmailBean implements CrudBean, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Email email = new Email();
	private Email emailSel;
	private List<Email> emails = new ArrayList<Email>();

	@Inject
	private EmailDao emailDao;

	public EmailBean() {
		super();
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Email getEmailSel() {
		return emailSel;
	}

	public void setEmailSel(Email emailSel) {
		this.emailSel = emailSel;
	}

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

	@PostConstruct
	public void init() {
		email = new Email();
		listarTodos();
	}

	@Override
	public void salvar() {
		if (email.getId() == null) {
			emailDao.insert(email);
		} else {
			emailDao.update(email);
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
		init();
	}

	@Override
	public void excluir() {
		if (emailSel != null) {
			emailDao.deleteByEntity(emailSel);
			init();
		}
		UtilBeanFaces.addMessage("Operação realizada com sucesso!", null, "INFO", null);
	}

	@Override
	public void buscar() {
		emails.clear();
		emails = emailDao.listByEntity(email);
		if (emails.size() == 0) {
			UtilBeanFaces.addMessage("Não existe telefones para serem exibidos", null, "WARNING", null);
		}
	}

	@Override
	public void editar() {
		setEmail(getEmailSel());
	}

	@Override
	public void limpar() {
		init();
	}

	@Override
	public void listarTodos() {
		emails = emailDao.listAll();
	}

}
