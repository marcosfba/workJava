/**
 * 
 */
package br.edu.unitri.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @author marcos.fernando
 *
 */
@RequestScoped
@ManagedBean
public class MenuBean implements Serializable {
	

	private static final long serialVersionUID = 1L;

	public void index() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("index.xhtml");
	}

	public void cadCategoria() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadCategoria.xhtml");
	}
	
	public void cadProduto() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadProduto.xhtml");
	}

	public void cadLogin() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadLogin.xhtml");
	}

	public void cadPedido() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadPedido.xhtml");
	}

	public void listarCategoria() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listCategoria.xhtml");
	}
	
	public void listarProduto() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listProduto.xhtml");
	}

	public void listarLogin() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listLogin.xhtml");
	}

	public void listarPedido() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listPedido.xhtml");
	}
	
	public void buscar(){
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação",
						"Metodo ainda não está implementado"));
	}

}
