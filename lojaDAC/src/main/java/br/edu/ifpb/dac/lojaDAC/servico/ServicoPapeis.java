package br.edu.ifpb.dac.lojaDAC.servico;


import java.util.List;


import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.lojaDAC.modelo.Papel;
import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;

//adicionar validação de acordo com o papel (nível de acesso)
@Stateless
public class ServicoPapeis {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] PapelDao foi criado.");
	}
	
	public void salva(Usuario usuario) {
		manager.merge(usuario);
		System.out.println("[INFO] Salvou o Usuario " + usuario.getNome());
	}
	
	public Papel buscaPelaId(Integer papelId) {
		Papel papel = manager.find(Papel.class,papelId);
		return papel;
	}
	
	public List<Papel> todosPapeis() {
	    System.out.println("[INFO] Consultando todos os papeis ");
		return manager.createQuery("select p from Papel p",Papel.class).getResultList();
	}
	
	

	
}
