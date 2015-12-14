/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;

import br.edu.unitri.enumerators.TipoTelefone;

/**
 * @author marcos.fernando
 *
 */
public class Telefone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	@Colunas(nome = "Tipo de Telefone", size = 100)
	private TipoTelefone tipoTelefone;
	@Colunas(nome = "Nº DDD", size = 85)
	private String numDdd;
	@Colunas(nome = "Nº do Telefone", size = 100)
	private String numTelefone;
	@Colunas(nome = "Nº Ramal", size = 80)
	private String numRamal;
	@Colunas(nome = "Cód. Area", size = 80)
	private String numArea;
	private Pessoa pessoa;

	public Telefone() {
		super();
	}

	public Telefone(TipoTelefone tipoTelefone, String numDdd, String numTelefone, String numRamal, String numArea) {
		super();
		this.tipoTelefone = tipoTelefone;
		this.numDdd = numDdd;
		this.numTelefone = numTelefone;
		this.numRamal = numRamal;
		this.numArea = numArea;
	}

	public Telefone(TipoTelefone tipoTelefone, String numDdd, String numTelefone, String numRamal, String numArea,
			Pessoa pessoa) {
		super();
		this.tipoTelefone = tipoTelefone;
		this.numDdd = numDdd;
		this.numTelefone = numTelefone;
		this.numRamal = numRamal;
		this.numArea = numArea;
		this.pessoa = pessoa;
	}

	public Telefone(Long id, TipoTelefone tipoTelefone, String numDdd, String numTelefone, String numRamal,
			String numArea, Pessoa pessoa) {
		super();
		this.id = id;
		this.tipoTelefone = tipoTelefone;
		this.numDdd = numDdd;
		this.numTelefone = numTelefone;
		this.numRamal = numRamal;
		this.numArea = numArea;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public String getNumDdd() {
		return numDdd;
	}

	public void setNumDdd(String numDdd) {
		this.numDdd = numDdd;
	}

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public String getNumRamal() {
		return numRamal;
	}

	public void setNumRamal(String numRamal) {
		this.numRamal = numRamal;
	}

	public String getNumArea() {
		return numArea;
	}

	public void setNumArea(String numArea) {
		this.numArea = numArea;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id=" + id + ", tipoTelefone=" + tipoTelefone + ", numDdd=" + numDdd + ", numTelefone="
				+ numTelefone + ", numRamal=" + numRamal + ", numArea=" + numArea + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
