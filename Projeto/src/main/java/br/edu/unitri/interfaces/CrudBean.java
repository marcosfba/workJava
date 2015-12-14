/**
 * 
 */
package br.edu.unitri.interfaces;

/**
 * @author marcos.fernando
 *
 */
public interface CrudBean {
	
	public abstract void salvar();
	public abstract void buscar();
	public abstract void excluir();
	public abstract void editar();
	public abstract void limpar();
	public abstract void listarTodos();

}
