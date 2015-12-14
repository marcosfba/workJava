/**
 * 
 */
package br.edu.unitri.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;

import br.edu.unitri.controler.CategoriaControler;
import br.edu.unitri.controler.FotoControler;
import br.edu.unitri.controler.ProdutoControler;
import br.edu.unitri.interfaces.Operacoes;
import br.edu.unitri.model.Categoria;
import br.edu.unitri.model.Foto;
import br.edu.unitri.model.Produto;

/**
 * @author marcos.fernando
 *
 */
@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable, Operacoes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private Foto foto = new Foto();
	private List<Foto> listFotos = new ArrayList<Foto>();
	private Produto prodSel;
	private List<Produto> listaProdutos = new ArrayList<Produto>();
	private List<Produto> listaProdutosSel = new ArrayList<Produto>();
	private List<Categoria> listaCategorias = new ArrayList<Categoria>();
	private ProdutoControler pdr = new ProdutoControler();
	private CategoriaControler mdr = new CategoriaControler();
	private FotoControler ftr = new FotoControler();

	public ProdutoBean() {
		super();
	}

	@PostConstruct
	public void init() {
		listarTodos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public Produto getProdSel() {
		return prodSel;
	}

	public void setProdSel(Produto prodSel) {
		this.prodSel = prodSel;
	}

	public Foto getFoto() {
		return foto;
	}

	public void setFoto(Foto foto) {
		this.foto = foto;
	}

	public List<Foto> getListFotos() {
		return listFotos;
	}

	public void setListFotos(List<Foto> listFotos) {
		this.listFotos = listFotos;
	}

	public void listarTodos() {
		try {
			listaProdutos = pdr.findAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao listar Produtos " + e.getMessage()));
		}
	}

	public List<Categoria> getListaCategorias() {
		try {
			listaCategorias = mdr.findAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao listar categorias " + e.getMessage()));
		}
		return listaCategorias;
	}

	public void setListaCategorias(List<Categoria> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<Produto> getListaProdutosSel() {
		return listaProdutosSel;
	}

	public void setListaProdutosSel(List<Produto> listaProdutosSel) {
		this.listaProdutosSel = listaProdutosSel;
	}

	@Override
	public void salvar() {
		try {
			if (pdr.save(getProduto()) != null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
								"Operação realizada com sucesso"));
			}
			listarTodos();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao incluir produto " + e.getMessage()));
		}
	}

	@Override
	public void buscar() {
	}

	@Override
	public void excluir() {
		if (getProdSel() != null) {
			try {
				pdr.delete(getProdSel());
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Informação", "Produto excluido com sucesso "));
				listarTodos();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Erro ao tentar excluir produto "
										+ e.getMessage()));
			}
		}
	}

	@Override
	public void editar() {
		setProduto(getProdSel());
	}

	@Override
	public void limpar() {
		produto = new Produto();
		listarTodos();
	}

	@Override
	public void alterar() {
		try {
			if (pdr.update(getProduto())) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
								"Operação realizada com sucesso"));
				listarTodos();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao editar produto " + e.getMessage()));
		}

	}

	public void salvaFoto() {
		try {
			ftr.save(getFoto());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao incluir produto " + e.getMessage()));
		} finally {
			foto = new Foto();
			listarTodos();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
							"Foto inserida com sucesso"));

		}
	}

	public void processFileUpload(FileUploadEvent uploadEvent) {
		try {
			foto.setProduto(prodSel);
			foto.setFotoImagem(uploadEvent.getFile().getContents());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void listaFotosProduto() {
		try {
			ServletContext sContext = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();
			listFotos = ftr.listByProdutos(prodSel);
			File folder = new File(sContext.getRealPath("/temp"));
			if (!folder.exists()) {
				folder.mkdirs();
			}
			for (Foto f : listFotos) {
				String nomeArquivo = f.getFotoId() + ".jpg";
				String arquivo = sContext.getRealPath("/temp") + File.separator
						+ nomeArquivo;
				criaArquivo(f.getFotoImagem(), arquivo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void criaArquivo(byte[] bytes, String arquivo) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
