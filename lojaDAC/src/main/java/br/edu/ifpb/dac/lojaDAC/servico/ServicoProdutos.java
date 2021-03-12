package br.edu.ifpb.dac.lojaDAC.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.lojaDAC.modelo.Produto;

//adicionar validação de acordo com o papel (nível de acesso)
@Stateless
public class ServicoProdutos {

	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] ProdutoDao foi criado.");
	}
	
	
	public void salva(Produto produto) {
		
		manager.merge(produto);
		System.out.println("[INFO] Salvou o Produto " + produto.getNome());
	}
	public void deletar(Produto produto) {
		
		manager.remove(manager.find(Produto.class, produto.getId()));
		System.out.println("[INFO] Deletou o produto" + produto.getNome());
	}
	
	
	public Produto buscaPelaId(Integer produtoId) {
	    System.out.println("[INFO] Consultando Produto pelo id: "+produtoId);

		Produto produto = manager.find(Produto.class,produtoId);
		
		return produto;
	}
	
	public List<Produto> todosProdutos() {
	    System.out.println("[INFO] Consultando todos os produtos");
		return manager.createQuery("select p from Produto p",Produto.class).getResultList();
	}
	
}
