/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbPedido")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date dtPedido;

	@OneToOne
	@JoinColumn(name = "idLogin", referencedColumnName = "id")
	private Login cliente;

	private String Observacao;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", referencedColumnName = "id")
	private Collection<ItensPedido> itens;

	public Pedido() {
		super();
	}

	public Pedido(long id, Date dtPedido, Login cliente, String observacao) {
		super();
		this.id = id;
		this.dtPedido = dtPedido;
		this.cliente = cliente;
		this.Observacao = observacao;
	}

	public Pedido(long id, Date dtPedido, Login cliente, String observacao,
			Collection<ItensPedido> itens) {
		super();
		this.id = id;
		this.dtPedido = dtPedido;
		this.cliente = cliente;
		this.Observacao = observacao;
		this.itens = itens;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	public Login getCliente() {
		return cliente;
	}

	public void setCliente(Login cliente) {
		this.cliente = cliente;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

	public Collection<ItensPedido> getItens() {
		return itens;
	}

	public void setItens(Collection<ItensPedido> itens) {
		this.itens = itens;
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
		Pedido other = (Pedido) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
