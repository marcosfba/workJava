/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;

import br.edu.unitri.enumerators.TipoEndereco;

/**
 * @author marcos.fernando
 *
 */
public class Endereco implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	@Colunas(nome = "Tipo de Endereço", size = 155)
	private TipoEndereco tipoEndereco;
	@Colunas(nome = "País", size = 70)
	private String pais;
	@Colunas(nome = "UF", size = 45)
	private String estado;
	@Colunas(nome = "Cidade", size = 100)
	private String cidade;
	@Colunas(nome = "Endereço/Logradouro", size = 170)
	private String logradouro;
	@Colunas(nome = "Nº", size = 35)
	private int numero;
	@Colunas(nome = "Complemento", size = 175)
	private String complemento;
	@Colunas(nome = "CEP", size = 75)
	private String cep;

	private Pessoa pessoa;

	public Endereco() {
		super();
	}

	public Endereco(TipoEndereco tipoEndereco, String pais, String estado, String cidade, String logradouro, int numero,
			String complemento, String cep, Pessoa pessoa) {
		super();
		this.tipoEndereco = tipoEndereco;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.pessoa = pessoa;
	}

	public Endereco(Long id, TipoEndereco tipoEndereco, String pais, String estado, String cidade, String logradouro,
			int numero, String complemento, String cep, Pessoa pessoa) {
		super();
		this.id = id;
		this.tipoEndereco = tipoEndereco;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.pessoa = pessoa;
	}

	public Endereco(TipoEndereco tipoEndereco, String pais, String estado, String cidade, String logradouro, int numero,
			String complemento, String cep) {
		super();
		this.tipoEndereco = tipoEndereco;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", tipoEndereco=" + tipoEndereco + ", pais=" + pais + ", estado=" + estado
				+ ", cidade=" + cidade + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", cep=" + cep + "]";
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
