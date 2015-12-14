/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.unitri.enumerators.TipoTelefone;
import br.edu.unitri.interfaces.SimpleEntity;

/**
 * @author marcos.fernando
 *
 */
@XmlRootElement
@Entity
@Table(name="tbTelefone")
public class Telefone implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TPTELEFONE")
	@Enumerated(EnumType.STRING)
	private TipoTelefone tipoTelefone;
	
	@Column(name = "DDD", length =10)
	private String numDdd;

	@Column(name = "NUMTELEFONE", length =20)
	private String numTelefone;

	@Column(name = "RAMAL", length =10)
	private String numRamal;

	@Column(name = "NUMAREA", length =10)
	private String numArea;
	
	@OneToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	private Pessoa pessoa;

	public Telefone() {
		super();
	}

	public Telefone(Long id, TipoTelefone tipoTelefone, String numDdd, String numTelefone, String numRamal,
			String numArea) {
		super();
		this.id = id;
		this.tipoTelefone = tipoTelefone;
		this.numDdd = numDdd;
		this.numTelefone = numTelefone;
		this.numRamal = numRamal;
		this.numArea = numArea;
	}

	public Telefone(TipoTelefone tipoTelefone, String numDdd, String numTelefone, String numRamal, String numArea) {
		super();
		this.tipoTelefone = tipoTelefone;
		this.numDdd = numDdd;
		this.numTelefone = numTelefone;
		this.numRamal = numRamal;
		this.numArea = numArea;
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
