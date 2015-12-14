/**
 * 
 */
package br.edu.unitri.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import br.edu.unitri.enumerators.TipoEmail;
import br.edu.unitri.enumerators.TipoEndereco;
import br.edu.unitri.enumerators.TipoPessoa;
import br.edu.unitri.enumerators.TipoTelefone;

/**
 * @author Marcos
 *
 */
@Named
@SessionScoped
public class ListUtilBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectItem> listTiposTelefones = getTiposTelefones();
	private List<SelectItem> listTiposEmails = getTiposEmails();
	private List<SelectItem> listTiposEnderecos = getTiposEnderecos();
	private List<SelectItem> listTiposPessoas = getTiposPessoas();
	private List<String> listUfs = getListaUfs();


	private List<SelectItem> getTiposTelefones() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoTelefone type : TipoTelefone.values()) {
			items.add(new SelectItem(type, type.getDescricao()));
		}
		return items;
	}

	private List<SelectItem> getTiposPessoas() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa type : TipoPessoa.values()) {
			items.add(new SelectItem(type, type.getDescricao()));
		}
		return items;
	}

	private List<String> getListaUfs() {
		String uf[] = { "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE",
				"PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO" };
		List<String> items = new ArrayList<String>();
		for (int i = 0; i < uf.length; i++) {
			items.add(uf[i]);
		}
		return items;
	}

	private List<SelectItem> getTiposEnderecos() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoEndereco type : TipoEndereco.values()) {
			items.add(new SelectItem(type, type.getDescricao()));
		}
		return items;
	}

	private List<SelectItem> getTiposEmails() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoEmail type : TipoEmail.values()) {
			items.add(new SelectItem(type, type.getDescricao()));
		}
		return items;
	}

	public List<SelectItem> getListTiposTelefones() {
		return listTiposTelefones;
	}

	public void setListTiposTelefones(List<SelectItem> listTiposTelefones) {
		this.listTiposTelefones = listTiposTelefones;
	}

	public List<SelectItem> getListTiposEmails() {
		return listTiposEmails;
	}

	public void setListTiposEmails(List<SelectItem> listTiposEmails) {
		this.listTiposEmails = listTiposEmails;
	}

	public List<SelectItem> getListTiposEnderecos() {
		return listTiposEnderecos;
	}

	public void setListTiposEnderecos(List<SelectItem> listTiposEnderecos) {
		this.listTiposEnderecos = listTiposEnderecos;
	}

	public List<String> getListUfs() {
		return listUfs;
	}

	public void setListUfs(List<String> listUfs) {
		this.listUfs = listUfs;
	}

	public List<SelectItem> getListTiposPessoas() {
		return listTiposPessoas;
	}

	public void setListTiposPessoas(List<SelectItem> listTiposPessoas) {
		this.listTiposPessoas = listTiposPessoas;
	}

}
