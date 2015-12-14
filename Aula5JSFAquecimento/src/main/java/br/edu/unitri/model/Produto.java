/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbProduto")
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private String codigo;
	private BigDecimal precoCusto;
	private BigDecimal precoVenda;
	private int quantidade;
	private int qtdEstoque;
	@OneToOne
	@JoinColumn(name = "idCategoria", referencedColumnName = "id")
	private Categoria categoria;
	@Temporal(TemporalType.DATE)
	private Date dataEntrada;
	@Transient
	private BigDecimal VlrTotal;

	public Produto() {
		super();
	}

	public Produto(Long id, String descricao, String codigo,
			BigDecimal precoCusto, BigDecimal precoVenda, int quantidade,
			int qtdEstoque, Categoria categoria, Date dataEntrada) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.codigo = codigo;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.qtdEstoque = qtdEstoque;
		this.categoria = categoria;
		this.dataEntrada = dataEntrada;
	}

	public Produto(String descricao, String codigo, BigDecimal precoCusto,
			BigDecimal precoVenda, int quantidade, int qtdEstoque,
			Categoria categoria, Date dataEntrada) {
		super();
		this.descricao = descricao;
		this.codigo = codigo;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.quantidade = quantidade;
		this.qtdEstoque = qtdEstoque;
		this.categoria = categoria;
		this.dataEntrada = dataEntrada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(int qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public BigDecimal getVlrTotal() {
		BigDecimal vr = new BigDecimal(getQuantidade()*getPrecoVenda().floatValue());
		return vr;
	}

	public void setVlrTotal(BigDecimal vlrTotal) {
		VlrTotal = vlrTotal;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
