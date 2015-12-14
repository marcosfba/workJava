/**
 * 
 */
package br.edu.unitri.bean;

import java.io.IOException;
import java.io.Serializable;

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

	public void listarCategoria() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadCategoria.xhtml");
	}
	
	public void listarProduto() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadProduto.xhtml");
	}

}
