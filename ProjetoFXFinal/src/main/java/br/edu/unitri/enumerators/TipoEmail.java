/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoEmail {
	
	EMPRI("Email Principal","EMAIL.PRI"), 
	EMSEC("Email Secundário","EMAIL.SEC"),
	EMCOM("Email Comercial","EMAIL.COM"),
	EMPES("Email Empresarial","EMAIL.EMP"),
	EMCON("Email Contato","EMAIL.CONT"),
	OUTROS("Outros","OUT");
	
	public final String descricao;
	public final String sigla;
	
	TipoEmail(String descricao, String sigla) {
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
