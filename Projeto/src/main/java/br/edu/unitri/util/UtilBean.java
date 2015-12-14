/**
 * 
 */
package br.edu.unitri.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * @author marcos.fernando
 *
 */
@Named
public class UtilBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String getAbsolutePathApp() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		int porta = request.getServerPort();
		String port = porta != 0 ? ":" + porta : "";
		String contextPath = request.getContextPath() != null ? request
				.getContextPath() : "";
		String absolutePathApp = request.getServerName() + port + contextPath;
		return "http://" + absolutePathApp;
	}
	


}
