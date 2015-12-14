/**
 * 
 */
package br.edu.unitri.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.event.CellEditEvent;

import br.edu.unitri.controler.ItensPedidoControler;
import br.edu.unitri.controler.LoginControler;
import br.edu.unitri.controler.PedidoControler;
import br.edu.unitri.controler.ProdutoControler;
import br.edu.unitri.interfaces.Operacoes;
import br.edu.unitri.model.ConsPedido;
import br.edu.unitri.model.Login;
import br.edu.unitri.model.Pedido;
import br.edu.unitri.model.Produto;
import br.edu.unitri.util.JpaUtil;

/**
 * @author marcos.fernando
 *
 */
@ManagedBean
@SessionScoped
public class PedidoBean implements Serializable, Operacoes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Pedido pedido = new Pedido();
	private Pedido pedidoSel;
	private List<Pedido> listaPedido = new ArrayList<Pedido>();
	private PedidoControler pedidoControler = new PedidoControler();
	private ConsPedido consulta = new ConsPedido();

	private List<Produto> listaProdutos = new ArrayList<Produto>();
	private Produto prodSel;
	private ProdutoControler produtoCtr = new ProdutoControler();

	private List<Produto> listaProdutosSel = new ArrayList<Produto>();
	private List<Login> listaClientes = new ArrayList<Login>();
	private LoginControler loginCtr = new LoginControler();
	private ItensPedidoControler itr = new ItensPedidoControler();


	public PedidoBean() {
		super();
	}

	@PostConstruct
	public void init() {
		preencherListas();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedidoSel() {
		return pedidoSel;
	}

	public void setPedidoSel(Pedido pedidoSel) {
		this.pedidoSel = pedidoSel;
	}

	public List<Pedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public List<Produto> getListaProdutosSel() {
		return listaProdutosSel;
	}

	public void setListaProdutosSel(List<Produto> listaProdutosSel) {
		this.listaProdutosSel = listaProdutosSel;
	}

	public Produto getProdSel() {
		return prodSel;
	}

	public void setProdSel(Produto prodSel) {
		this.prodSel = prodSel;
	}

	public List<Login> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Login> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public ConsPedido getConsulta() {
		return consulta;
	}

	public void setConsulta(ConsPedido consulta) {
		this.consulta = consulta;
	}

	public void selecionarProduto() {
		if (getProdSel() != null) {
			if (listaProdutosSel.contains(getProdSel())) {
				getProdSel().setQuantidade(getProdSel().getQuantidade() + 1);
				listaProdutosSel.remove(getProdSel());
				listaProdutosSel.add(getProdSel());
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Informação",
										"Produto adicionado com sucesso "));
			} else {
				getProdSel().setQuantidade(1);
				listaProdutosSel.add(getProdSel());
				FacesContext.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(FacesMessage.SEVERITY_INFO,
										"Informação",
										"Produto adicionado com sucesso "));
			}
		}
	}

	private void preencherListas() {
		try {
			listaProdutos = produtoCtr.findAll();
			listaClientes = loginCtr.findAll();
			listaPedido = pedidoControler.findAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao preencher listas " + e.getMessage()));
		}
	}

	private void limparPedido() {
		pedido = new Pedido();
		listaProdutosSel.clear();
	}

	@Override
	public void salvar() {
		try {
			if (pedidoControler.save(getPedido()) != null) {
				itr.savePedLista(getListaProdutosSel(), getPedido());
				limparPedido();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
								"Operação realizada com sucesso"));

				preencherListas();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao incluir Pedido " + e.getMessage()));
		}
	}

	@Override
	public void excluir() {
		if (getPedidoSel() != null) {
			try {
				itr.removeItensByPedido(getPedidoSel());
				pedidoControler.delete(getPedidoSel());
				limparPedido();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Informação", "Pedido excluido com sucesso "));
				preencherListas();
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
								"Erro ao tentar excluir Pedido "
										+ e.getMessage()));
			}
		}
	}

	@Override
	public void editar() {
		setPedido(getPedidoSel());
	}

	@Override
	public void limpar() {
		pedido = new Pedido();
		consulta = new ConsPedido();
		preencherListas();
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Quantidade alterada com sucesso", "Valor original: "
							+ oldValue + ", Novo valor:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	@Override
	public void buscar() {
		
		CriteriaBuilder cb = JpaUtil.getManager().getCriteriaBuilder();
		CriteriaQuery<Pedido> query = cb.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);
		Predicate where1 = null, where2 = null;
		if (getConsulta().getId() != null) {
			where1 = cb.equal(root.get("id"), getConsulta().getId());
		}
		if (getConsulta().getDtPedido() != null) {
			where2 = cb.equal(root.get("dtPedido"), getConsulta().getDtPedido());
		}
		List<Predicate> predicados = new ArrayList<Predicate>(); 
		if (where1 != null) { predicados.add(where1) ; }
		if (where2 != null) { predicados.add(where2) ; }
		
		query.where(cb.and(predicados.toArray(new Predicate[]{})));

		try {
			listaPedido.clear();
			System.out.println("query da consulta criteria");
			listaPedido = JpaUtil.getManager().createQuery(query).getResultList();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro",
							"Erro ao buscar pedidos " + e.getMessage()));
		}
	}

	@Override
	public void alterar() {
		// do not implement
	}

}
