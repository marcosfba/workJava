/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author marcos.fernando
 *
 */
public class ConsPedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Date dtPedido;

	public ConsPedido() {
		super();
	}

	public ConsPedido(Long id, Date dtPedido) {
		super();
		this.id = id;
		this.dtPedido = dtPedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

}
