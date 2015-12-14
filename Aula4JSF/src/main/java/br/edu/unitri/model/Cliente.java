/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbCliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String Nome;
	private String Email;
	private String Cpf;
	private String Identidade;
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;

	public Cliente() {
		super();
	}

	public Cliente(long id, String nome, String email, String cpf,
			String identidade, Date dtNascimento) {
		super();
		this.id = id;
		this.Nome = nome;
		this.Email = email;
		this.Cpf = cpf;
		this.Identidade = identidade;
		this.dtNascimento = dtNascimento;
	}

	public Cliente(String nome, String email, String cpf, String identidade,
			Date dtNascimento) {
		super();
		this.Nome = nome;
		this.Email = email;
		this.Cpf = cpf;
		this.Identidade = identidade;
		this.dtNascimento = dtNascimento;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getIdentidade() {
		return Identidade;
	}

	public void setIdentidade(String identidade) {
		Identidade = identidade;
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
