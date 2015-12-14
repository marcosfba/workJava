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
	
	public void marca() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadMarca.xhtml");
	}
	public void produto() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("cadProduto.xhtml");
	}
}
