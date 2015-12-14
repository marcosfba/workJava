/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;

import br.edu.unitri.enumerators.TipoEmail;

/**
 * @author marcos.fernando
 *
 */
public class Email implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	@Colunas(nome = "Tipo de Email", size = 155)
	private TipoEmail tipoEmail;
	@Colunas(nome = "Descrição do Email", size = 155)
	private String descricao;

	private Pessoa pessoa;

	public Email() {
		super();
	}

	public Email(TipoEmail tipoEmail, String descricao) {
		super();
		this.tipoEmail = tipoEmail;
		this.descricao = descricao;
	}

	public Email(TipoEmail tipoEmail, String descricao, Pessoa pessoa) {
		super();
		this.tipoEmail = tipoEmail;
		this.descricao = descricao;
		this.pessoa = pessoa;
	}

	public Email(Long id, TipoEmail tipoEmail, String descricao, Pessoa pessoa) {
		super();
		this.id = id;
		this.tipoEmail = tipoEmail;
		this.descricao = descricao;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEmail getTipoEmail() {
		return tipoEmail;
	}

	public void setTipoEmail(TipoEmail tipoEmail) {
		this.tipoEmail = tipoEmail;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Email [id=" + id + ", tipoEmail=" + tipoEmail + ", descricao=" + descricao + "]";
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
		Email other = (Email) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
