/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.unitri.enumerators.TipoPessoa;
import br.edu.unitri.interfaces.SimpleEntity;

/**
 * @author marcos.fernando
 *
 */
@XmlRootElement
@Entity
@Table(name = "tbPessoa")
public class Pessoa implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TPPESSOA")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;

	@Column(name = "NOMPESSOA", length = 100)
	private String nome;

	@Column(name = "DTNASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;

	public Pessoa() {
		super();
	}

	public Pessoa(Long id, TipoPessoa tipoPessoa, String nome, Date dtNascimento) {
		super();
		this.id = id;
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", tipoPessoa=" + tipoPessoa + ", nome=" + nome + ", dtNascimento=" + dtNascimento
				+ "]";
	}

}
