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

import br.edu.unitri.controler.ClienteControler;
import br.edu.unitri.interfaces.Operacoes;
import br.edu.unitri.model.Cliente;
import br.edu.unitri.model.Consulta;

/**
 * @author marcos.fernando
 *
 */
@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable, Operacoes {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cliente cliente = new Cliente();
	private Cliente clienteSel;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private ClienteControler pdr = new ClienteControler();
	private Consulta consulta = new Consulta();

	public ClienteBean() {
		super();
	}
	
	@PostConstruct
	public void init(){
		listarTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public Cliente getClienteSel() {
		return clienteSel;
	}

	public void setClienteSel(Cliente clienteSel) {
		this.clienteSel = clienteSel;
	}

	public void listarTodos(){
		try {
			listaClientes = pdr.findAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao listar Clientes " + e.getMessage()));
		}
	}
	
	@Override
	public void salvar() {
		try {
			if (pdr.save(getCliente()) != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
								"Operação realizada com sucesso"));
			}
			listarTodos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao incluir cliente " + e.getMessage()));
		}
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	@Override
	public void buscar() {
		String sql = "select c.* from tbCliente c ";
		String param ="";
		
		if (getConsulta().getId() != null) {
			if (getConsulta().getId()!= 0l) {
				param = " c.id =" + getConsulta().getId() ;
			}
		}
		if (getConsulta().getDescricao()!= null) {
			if (!getConsulta().getDescricao().isEmpty()) {
				if (!param.isEmpty()) {
					param += " and c.Nome like  '%"+ getConsulta().getDescricao() + "%'";
				} else {
					param = " c.Nome like  '%"+ getConsulta().getDescricao() + "%'";
				}
			}
		}
		if (!param.isEmpty()) {
			param = " where " + param;
		}
		
		try {
			listaClientes.clear();
			listaClientes = pdr.findAll(sql,param);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao buscar Categorias " + e.getMessage()));
		}
	}

	@Override
	public void excluir() {
		if (getClienteSel()!=null) {
			try {
				pdr.delete(getClienteSel());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
								"Cliente excluido com sucesso "));
				listarTodos();				
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Erro ao tentar excluir cliente " + e.getMessage()));
			}
		}
	}

	@Override
	public void editar() {
		setCliente(getClienteSel());
	}

	@Override
	public void limpar() {
		cliente = new Cliente();
		consulta = new Consulta();
		listarTodos();

	}

	@Override
	public void alterar() {
		try {
			if (pdr.update(getCliente())) {
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
							"Erro ao editar cliente " + e.getMessage()));
		}
		
	}

}
