/**
 * 
 */
package br.edu.unitri.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.edu.unitri.enumerators.TipoEmail;

/**
 * @author Marcos
 *
 */
@Named
@FacesConverter(value ="tipoEmailConverter")
public class TipoEmailConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg0 == null) {
			throw new NullPointerException("context");
		}
		if (arg1 == null) {
			throw new NullPointerException("component");
		}

		TipoEmail tipoEmail = null;
		if (arg2 != null && !arg2.equalsIgnoreCase("")
				&& arg2.trim().length() > 0) {
			for (TipoEmail type : TipoEmail.values()) {
				String desc = type.getDescricao();
				if (desc.equalsIgnoreCase(arg2)) {
					tipoEmail = type;
					break;				
				}
			}
			if (tipoEmail == null) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Valor não encontrado",
						"Tipo de Email inválido!");
				throw new ConverterException(message);
			}
		}
		return tipoEmail;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg0 == null) {
			throw new NullPointerException("context");
		}
		if (arg1 == null) {
			throw new NullPointerException("component");
		}
		if (arg2 instanceof TipoEmail) {
			return ((TipoEmail)arg2).toString();
		} else {
			return "";
		}
	}

}
