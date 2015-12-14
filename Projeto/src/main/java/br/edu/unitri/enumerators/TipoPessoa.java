/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoPessoa {
	
	PESFIS("Pessoa Fisica","PES.FIS"), 
	PESJUR("Pessoa Juridica","PES.JUR");
	
	public final String descricao;
	public final String sigla;
	
	TipoPessoa(String descricao, String sigla) {
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
