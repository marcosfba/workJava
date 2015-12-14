/**
 * 
 */
package br.edu.unitri.Converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unitri.controler.LoginControler;
import br.edu.unitri.model.Login;

/**
 * @author marcos.fernando
 *
 */
@FacesConverter(forClass=Login.class)
public class LoginConverter implements Converter {
	
	private LoginControler ctr = new LoginControler();
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Integer idLogin = Integer.parseInt(arg2);
		Login cliente = null;
		try {
		   cliente = ctr.getById(idLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return ((Login) arg2).getId().toString();
	}

}
