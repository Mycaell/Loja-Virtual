package br.edu.ifpb.dac.lojaDAC.servico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.ifpb.dac.lojaDAC.modelo.Compra;
import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;

//adicionar validação de acordo com o papel (nível de acesso)
@Stateless
public class ServicoCompras {

	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] CompraDao foi criado.");
	}
	
	public void salva(Compra compra) {
		
		manager.merge(compra);
		System.out.println("[INFO] Compra efetuada!");
	}

	public List<Compra> todasCompras() {
	    System.out.println("[INFO] Consultando todas as compras");
		return manager.createQuery("select c from Compra c",Compra.class).getResultList();
	}
	

	public List<Compra> minhasCompras(Usuario usuario) {
	    System.out.println("[INFO] Consultando todas as compras de um usuário em especifico");
	    
	    String jpql = "select c from Compra c where c.cliente = :usuario";
	    TypedQuery<Compra> typedQuery = manager.createQuery(jpql, Compra.class);
	    typedQuery.setParameter("usuario", usuario);
	    List<Compra> compras = (List<Compra>) typedQuery.getResultList();
	    
	    
		return compras;
	}
	
	
	
	
}
