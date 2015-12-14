/**
 * 
 */
package br.edu.unitri.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * @author marcos.fernando
 *
 */
@Named
public class UtilBeanFaces implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void navegador(String pagina) {
		FacesContext fc = FacesContext.getCurrentInstance();
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		nh.handleNavigation(fc, null, pagina.concat("?faces-redirect=true"));
	}

	
	public static void addMessage(String msg, String cliente, String tipo,  String erro) {
		switch (tipo) {
		case "INFO":
			FacesContext.getCurrentInstance().addMessage(cliente,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", msg));
			break;
		case "ERRO":
			FacesContext.getCurrentInstance().addMessage(cliente,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg + " " + erro));
			break;
		case "WARNING":
			FacesContext.getCurrentInstance().addMessage(cliente,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", msg));
			break;
		}
	}



}
