/**
 * 
 */
package br.edu.unitri.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * @author marcos.fernando
 *
 */
@RequestScoped
@ManagedBean
public class MenuBean {

	public void index() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("index.xhtml");
	}

	public void categoria() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadCategoria.xhtml");
	}
	
	public void cliente() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadCliente.xhtml");
	}
	public void produto() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadProduto.xhtml");
	}

	public void listCategoria() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listCategoria.xhtml");
	}

	public void listProduto() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listProduto.xhtml");
	}

	public void listCliente() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("listCliente.xhtml");
	}
}
