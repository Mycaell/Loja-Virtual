package br.edu.ifpb.dac.lojaDAC.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.lojaDAC.modelo.Categoria;
import br.edu.ifpb.dac.lojaDAC.modelo.Produto;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoCategorias;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoProdutos;

@Named
@ViewScoped
public class ProdutoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Produto produto;
	private List<Produto> produtos;
	
	private Integer idCategoria;
	private List<Categoria> categorias;
	
	
	@EJB
	private ServicoProdutos dao;

	@EJB
	private ServicoCategorias categoriaDao;
	
	
	
	public ProdutoBean() {
		
	}
	
	@PostConstruct
	public void init() {
		produto = new Produto();
		produtos = dao.todosProdutos();
		categorias = categoriaDao.todasCategorias();
		
	}
	
	public void cadastra() {
		System.out.println("Cadastra - Produto: "+produto.getNome());
		produto.setCategoria(categoriaDao.buscaPelaId(idCategoria));
		
		
		this.dao.salva(produto);
		
//		o ideal era deixar assim, mas isso impacta em um clone temporário quando utiliza a feature de editar
//		usuarios.add(usuario);
	
		produtos = this.dao.todosProdutos();

		this.produto = new Produto();
		
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
	}
	
	public void deletar() {
		
		System.out.println("Deletar - Produto: "+produto.getNome());
		
		this.dao.deletar(produto);
		
		produtos.remove(produto);
	
		this.produto = new Produto();
		
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Exluído com sucesso!"));
	}
	
	public void limpar() {
		System.out.println("LIMPOU");
		produto = new Produto();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ServicoProdutos getDao() {
		return dao;
	}

	public void setDao(ServicoProdutos dao) {
		this.dao = dao;
	}

	public ServicoCategorias getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(ServicoCategorias categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	

	
	
}
