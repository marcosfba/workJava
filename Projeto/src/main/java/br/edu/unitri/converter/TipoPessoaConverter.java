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

import br.edu.unitri.enumerators.TipoPessoa;

/**
 * @author Marcos
 *
 */
@Named
@FacesConverter(value ="tipoPessoaConverter")
public class TipoPessoaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg0 == null) {
			throw new NullPointerException("context");
		}
		if (arg1 == null) {
			throw new NullPointerException("component");
		}

		TipoPessoa tipoPessoa = null;
		if (arg2 != null && !arg2.equalsIgnoreCase("")
				&& arg2.trim().length() > 0) {
			for (TipoPessoa type : TipoPessoa.values()) {
				String desc = type.getDescricao();
				if (desc.equalsIgnoreCase(arg2)) {
					tipoPessoa = type;
					break;				
				}
			}
			if (tipoPessoa == null) {
				FacesMessage message = new FacesMessage(
						FacesMessage.SEVERITY_ERROR, "Valor não encontrado",
						"Tipo de Pessoa inválido!");
				throw new ConverterException(message);
			}
		}
		return tipoPessoa;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg0 == null) {
			throw new NullPointerException("context");
		}
		if (arg1 == null) {
			throw new NullPointerException("component");
		}
		if (arg2 instanceof TipoPessoa) {
			return ((TipoPessoa)arg2).toString();
		} else {
			return "";
		}
	}

}
