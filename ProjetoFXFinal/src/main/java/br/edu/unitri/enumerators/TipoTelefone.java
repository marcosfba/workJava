/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoTelefone {
	
	TELRES("Telefone Residencial","TEL.RES"), 
	TELCEL("Telefone Celular","TEL.CEL"),
	TELCON("Telefone Contato","TEL.CON"),
	TELCOM("Telefone Comercial","TEL.COM"),
	TELREC("Telefone Recado","TEL.REC"),
	OUTROS("Outros","OUT");
	
	public final String descricao;
	public final String sigla;
	
	TipoTelefone(String descricao, String sigla) {
       this.descricao = descricao;
       this.sigla = sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getSigla() {
		return sigla;
	}

	@Override
	public String toString(){
		return this.descricao;
	}

}
