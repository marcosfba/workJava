/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.util.Date;

import br.edu.unitri.enumerators.TipoPessoa;

/**
 * @author marcos.fernando
 *
 */
public class Pessoa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	@Colunas(nome = "Tipo de Pessoa", size = 100)
	private TipoPessoa tipoPessoa;
	@Colunas(nome = "Nome da Pessoa", size = 220)
	private String nome;
	@Colunas(nome = "Data de Nascimento", size = 95)
	private Date dtNascimento;

	public Pessoa() {
		super();
	}

	public Pessoa(TipoPessoa tipoPessoa, String nome, Date dtNascimento) {
		super();
		this.tipoPessoa = tipoPessoa;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
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
	public String toString() {
		return nome;
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

}
