/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tb_ItensPedido")
public class ItensPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idItem;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Pedido pedido;

	@OneToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;

	private int QtdItem;
	private BigDecimal VlrItem;
	private BigDecimal VlrDesconto;

	public ItensPedido() {
		super();
	}

	public ItensPedido(long idItem, Pedido pedido, Produto produto,
			int qtdItem, BigDecimal vlrItem, BigDecimal vlrDesconto) {
		super();
		this.idItem = idItem;
		this.pedido = pedido;
		this.produto = produto;
		this.QtdItem = qtdItem;
		this.VlrItem = vlrItem;
		this.VlrDesconto = vlrDesconto;
	}
	
	public ItensPedido(Pedido pedido, Produto produto, int qtdItem,
			BigDecimal vlrItem, BigDecimal vlrDesconto) {
		super();
		this.pedido = pedido;
		this.produto = produto;
		this.QtdItem = qtdItem;
		this.VlrItem = vlrItem;
		this.VlrDesconto = vlrDesconto;
	}

	public long getIdItem() {
		return idItem;
	}

	public void setIdItem(long idItem) {
		this.idItem = idItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQtdItem() {
		return QtdItem;
	}

	public void setQtdItem(int qtdItem) {
		QtdItem = qtdItem;
	}

	public BigDecimal getVlrItem() {
		return VlrItem;
	}

	public void setVlrItem(BigDecimal vlrItem) {
		VlrItem = vlrItem;
	}

	public BigDecimal getVlrDesconto() {
		return VlrDesconto;
	}

	public void setVlrDesconto(BigDecimal vlrDesconto) {
		VlrDesconto = vlrDesconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idItem ^ (idItem >>> 32));
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
		ItensPedido other = (ItensPedido) obj;
		if (idItem != other.idItem)
			return false;
		return true;
	}

}
