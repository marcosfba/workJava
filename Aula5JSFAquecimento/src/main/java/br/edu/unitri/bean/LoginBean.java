/**
 * 
 */
package br.edu.unitri.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.unitri.controler.LoginControler;
import br.edu.unitri.interfaces.Operacoes;
import br.edu.unitri.model.Consulta;
import br.edu.unitri.model.Login;

/**
 * @author marcos.fernando
 *
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable, Operacoes {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Login login = new Login();
	private Login loginSel;
	private List<Login> listaLogin = new ArrayList<Login>();
	private LoginControler loginControler = new LoginControler();
	private Consulta consulta = new Consulta();

	
	public LoginBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		listarTodos();
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Login getLoginSel() {
		return loginSel;
	}

	public void setLoginSel(Login loginSel) {
		this.loginSel = loginSel;
	}

	public List<Login> getListaLogin() {
		return listaLogin;
	}

	public void setListaLogin(List<Login> listaLogin) {
		this.listaLogin = listaLogin;
	}
	
	public void listarTodos(){
		try {
			listaLogin = loginControler.findAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao listar Logins " + e.getMessage()));
		}
	}

	@Override
	public void salvar() {
		try {
			if (loginControler.save(getLogin()) != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
								"Operação realizada com sucesso"));
			   listarTodos();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao incluir Login " + e.getMessage()));
		}
	}

	@Override
	public void excluir() {
		if (getLoginSel()!=null) {
			try {
				loginControler.delete(getLoginSel());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
								"Login excluido com sucesso "));
				listarTodos();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Erro ao tentar excluir Login " + e.getMessage()));
			}
		}
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	@Override
	public void editar() {
		setLogin(getLoginSel());
	}
	
	@Override
	public void limpar() {
		login = new Login();	
		consulta = new Consulta();
		listarTodos();
	}

	@Override
	public void buscar() {
		String sql = "select l.* from tbLogin l ";
		String param ="";
		
		if (getConsulta().getId() != null) {
			if (getConsulta().getId()!= 0l) {
				param = " l.id =" + getConsulta().getId() ;
			}
		}
		if (getConsulta().getDescricao()!= null) {
			if (!getConsulta().getDescricao().isEmpty()) {
				if (!param.isEmpty()) {
					param += " and ((l.userName like  '%"+ getConsulta().getDescricao() + "%') or"
							+ " (l.nome like '%"+ getConsulta().getDescricao() + "%'))";
				} else {
					param = " ((l.userName like  '%"+ getConsulta().getDescricao() + "%') or"
							+ " (l.nome like '%"+ getConsulta().getDescricao() + "%'))";
				}
			}
		}
		if (!param.isEmpty()) {
			param = " where " + param;
		}
		
		try {
			listaLogin.clear();
			listaLogin = loginControler.findAll(sql,param);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao buscar Categorias " + e.getMessage()));
		}
	}

	@Override
	public void alterar() {
		try {
			if (loginControler.update(getLogin())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
								"Operação realizada com sucesso"));
				listarTodos();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao editar produto " + e.getMessage()));
		}
	}

}
 