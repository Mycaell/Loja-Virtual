package br.edu.ifpb.dac.lojaDAC.servico;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.lojaDAC.modelo.Categoria;

//adicionar validação de acordo com o papel (nível de acesso)
@Stateless
public class ServicoCategorias {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] CategoriaDao foi criado.");
	}
	
	public void salva(Categoria categoria) {
		manager.merge(categoria);
		System.out.println("[INFO] Salvou o Usuario " + categoria.getNome());
	}
	
	public Categoria buscaPelaId(Integer categoriaId) {
		Categoria categoria = manager.find(Categoria.class,categoriaId);
		return categoria;
	}

	public List<Categoria> todasCategorias() {
	    System.out.println("[INFO] Consultando todas as Categorias");
		return manager.createQuery("select c from Categoria c",Categoria.class).getResultList();
	}
	
	

	
}
