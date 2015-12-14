/**
 * 
 */
package br.edu.unitri.enumerators;

/**
 * @author marcos.fernando
 *
 */
public enum TipoEndereco {
	
	ENDRES("Endereco Residencial","END.RES"), 
	ENDCOM("Endereco Comercial","END.COM"),
	ENDALU("Endereco Aluguel","END.ALU"),
	ENDREF("Endereco Referencia","END.REF"),
	ENDMAE("Endereco Casa Mae","END.MAE"),
	ENDPAI("Endereco Casa Pai","END.PAI"),
	OUTROS("Outros","OUT");
	
	public final String descricao;
	public final String sigla;
	
	TipoEndereco(String descricao, String sigla) {
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
