/**
 * 
 */
package br.edu.unitri.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author marcos.fernando
 *
 */
@Entity
@Table(name = "tbFoto")
public class Foto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long fotoId;

	private String fotoDescricao;

	@Lob
	private byte[] fotoImagem;

	@ManyToOne
	@JoinColumn(name = "PROD_ID", referencedColumnName = "ID")
	private Produto produto;

	public Foto() {
		super();
	}

	public Foto(Long fotoId) {
		super();
		this.fotoId = fotoId;
	}

	public Long getFotoId() {
		return fotoId;
	}

	public void setFotoId(Long fotoId) {
		this.fotoId = fotoId;
	}

	public String getFotoDescricao() {
		return fotoDescricao;
	}

	public void setFotoDescricao(String fotoDescricao) {
		this.fotoDescricao = fotoDescricao;
	}

	public byte[] getFotoImagem() {
		return fotoImagem;
	}

	public void setFotoImagem(byte[] fotoImagem) {
		this.fotoImagem = fotoImagem;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fotoId == null) ? 0 : fotoId.hashCode());
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
		Foto other = (Foto) obj;
		if (fotoId == null) {
			if (other.fotoId != null)
				return false;
		} else if (!fotoId.equals(other.fotoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Foto [fotoId=" + fotoId + ", fotoDescricao=" + fotoDescricao
				+ ", fotoImagem=" + Arrays.toString(fotoImagem) + ", produto="
				+ produto + "]";
	}

}
