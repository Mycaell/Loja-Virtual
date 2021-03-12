package br.edu.ifpb.dac.lojaDAC.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.dac.lojaDAC.modelo.Compra;
import br.edu.ifpb.dac.lojaDAC.modelo.Endereco;
import br.edu.ifpb.dac.lojaDAC.modelo.Pagamento;
import br.edu.ifpb.dac.lojaDAC.modelo.Produto;
import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoCompras;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoPagamentos;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoProdutos;

@Named
@javax.enterprise.context.SessionScoped
public class CompraBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Compra compra;
	
	@Inject
	LoginBean login;
	
	private List<Compra> compras;
	private boolean buscarComprasDoUsuario;
	
	private Produto produto;
	private Integer idFormaPagamento;
	
	private List<Pagamento> formasDePagamento;
	
	
//	produtos da compra /\
	private List<Produto> produtosNoCarrinho;
	private BigDecimal valorProdutosNoCarrinho;
	private int quantidadeProdutosNoCarrinho;
	
	@EJB
	private ServicoCompras dao;
	
	@EJB
	private ServicoPagamentos pagamentoDao;
	
	@EJB
	private ServicoProdutos produtoDao;
	
	

	public CompraBean() {}
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuario.setEndereco(new Endereco());
		compra = new Compra();
		produto = new Produto();
		produtosNoCarrinho = new ArrayList<Produto>();
		valorProdutosNoCarrinho = new BigDecimal("0.00");
		formasDePagamento = pagamentoDao.todasFormasPagamentos();
		buscarComprasDoUsuario = false;
		System.out.println("compra dao ----------------");
		
		usuario = login.getUsuarioLogado();
	}
	
	public void cadastra() {
		System.out.println("Cadastra - Compra: "+valorProdutosNoCarrinho);
//		usuário logado \/
		compra.setCliente(usuario);
		
		compra.setQuantidadeItens(quantidadeProdutosNoCarrinho);
		compra.setValorTotal(valorProdutosNoCarrinho);
		compra.setProdutos(produtosNoCarrinho);
		compra.setPagamento(pagamentoDao.buscaPelaId(idFormaPagamento));
		compra.setData(Calendar.getInstance().getTime());
		
		
		
		this.dao.salva(compra);
		
		
		
//		limpar o carrinho 
		compra = new Compra();
		produto = new Produto();
		quantidadeProdutosNoCarrinho = 0;
		produtosNoCarrinho = new ArrayList<Produto>();
		valorProdutosNoCarrinho = new BigDecimal("0.00");

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Compra efetuada com sucesso!"));
	}
	
	
	
	public void adicionarProduto() {
		System.out.println("produto: "+produto.getNome()+" adicionado ao carrinho! preco:"+produto.getPreco());
		produtosNoCarrinho.add(produto);

		
		valorProdutosNoCarrinho = valorProdutosNoCarrinho.add(produto.getPreco());

		quantidadeProdutosNoCarrinho++;
		
	
		
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Produto adicionado ao carrinho!"));
	}

//	buscar compras (será utilizado na feature de gerenciamento de compras)
	
	public void atualizaFlagCompras() {
		System.out.println("atualizando a flag da busca de compras");
		buscarComprasDoUsuario = true;
	}
	
	public List<Compra> buscarCompras() {
		
		compras = null;
		
		if(buscarComprasDoUsuario) {
			System.out.println("Compras de um usário");
			buscarComprasDoUsuario = false;
			compras = dao.minhasCompras(usuario);
		}
		
		compras = dao.todasCompras();
		
		return compras;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutosNoCarrinho() {
		return produtosNoCarrinho;
	}

	public void setProdutosNoCarrinho(List<Produto> produtosNoCarrinho) {
		this.produtosNoCarrinho = produtosNoCarrinho;
	}

	public BigDecimal getValorProdutosNoCarrinho() {
		return valorProdutosNoCarrinho;
	}

	public void setValorProdutosNoCarrinho(BigDecimal valorProdutosNoCarrinho) {
		this.valorProdutosNoCarrinho = valorProdutosNoCarrinho;
	}

	public int getQuantidadeProdutosNoCarrinho() {
		return quantidadeProdutosNoCarrinho;
	}

	public void setQuantidadeProdutosNoCarrinho(int quantidadeProdutosNoCarrinho) {
		this.quantidadeProdutosNoCarrinho = quantidadeProdutosNoCarrinho;
	}

	public ServicoCompras getDao() {
		return dao;
	}

	public void setDao(ServicoCompras dao) {
		this.dao = dao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public List<Pagamento> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(List<Pagamento> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public LoginBean getLogin() {
		return login;
	}

	public void setLogin(LoginBean login) {
		this.login = login;
	}

	public boolean isBuscarComprasDoUsuario() {
		return buscarComprasDoUsuario;
	}

	public void setBuscarComprasDoUsuario(boolean buscarComprasDoUsuario) {
		this.buscarComprasDoUsuario = buscarComprasDoUsuario;
	}

	public ServicoPagamentos getPagamentoDao() {
		return pagamentoDao;
	}

	public void setPagamentoDao(ServicoPagamentos pagamentoDao) {
		this.pagamentoDao = pagamentoDao;
	}

	public ServicoProdutos getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ServicoProdutos produtoDao) {
		this.produtoDao = produtoDao;
	}
	
	
	
	
	

	
	
}
