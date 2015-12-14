/**
 * 
 */
package br.edu.unitri.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.unitri.util.UtilBeanFaces;

/**
 * @author marcos.fernando
 *
 */
@Named
@SessionScoped
public class MenuBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void telefone() throws IOException {
		UtilBeanFaces.navegador("cadTelefone.xhtml");
	}

	public void endereco() throws IOException {
		UtilBeanFaces.navegador("cadEndereco.xhtml");
	}
	
	public void pessoa() throws IOException {
		UtilBeanFaces.navegador("cadPessoa.xhtml");
	}

	public void email() throws IOException {
		UtilBeanFaces.navegador("cadEmail.xhtml");
	}

	public void contato() throws IOException {
		UtilBeanFaces.navegador("cadContato.xhtml");
	}

	public void consTelefone() throws IOException {
		UtilBeanFaces.navegador("consTelefone.xhtml");
	}

	public void consEndereco() throws IOException {
		UtilBeanFaces.navegador("consEndereco.xhtml");
	}
	
	public void consPessoa() throws IOException {
		UtilBeanFaces.navegador("consPessoa.xhtml");
	}

	public void consEmail() throws IOException {
		UtilBeanFaces.navegador("consEmail.xhtml");
	}
	
	

}
