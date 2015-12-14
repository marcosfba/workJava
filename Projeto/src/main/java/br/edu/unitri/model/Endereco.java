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

import br.edu.unitri.enumerators.TipoEndereco;
import br.edu.unitri.interfaces.SimpleEntity;

/**
 * @author marcos.fernando
 *
 */
@XmlRootElement
@Entity
@Table(name="tbEndereco")
public class Endereco implements Serializable, SimpleEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TPENDERECO")
	@Enumerated(EnumType.STRING)
	private TipoEndereco tipoEndereco;

	@Column(name = "DESCPAIS", length =75)
	private String pais;
	
	@Column(name = "UF", length = 02)
	private String estado;
	
	@Column(name = "NOMCIDADE", length = 75)
	private String cidade;
	
	@Column(name = "ENDERECO", length = 150)
	private String logradouro;
	
	@Column(name = "NUM", length = 10)
	private int numero;
	
	@Column(name = "COMPLEMENTO", length = 75)
	private String complemento;
	
	@Column(name = "CEP", length = 15)
	private String cep;

	@OneToOne
	@JoinColumn(name = "pessoa_id", referencedColumnName = "id")
	private Pessoa pessoa;

	public Endereco() {
		super();
	}

	public Endereco(Long id, TipoEndereco tipoEndereco, String pais, String estado, String cidade, String logradouro,
			int numero, String complemento, String cep) {
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
