package br.edu.ifpb.dac.lojaDAC.servico;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.dac.lojaDAC.modelo.Pagamento;

//adicionar validação de acordo com o papel (nível de acesso)
@Stateless
public class ServicoPagamentos {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] PagamentoDAO foi criado.");
	}
	
	public void salva(Pagamento pagamento) {
		manager.merge(pagamento);
		System.out.println("[INFO] Salvou o Pagamento" + pagamento.getTipoPagamento());
	}
	
	public Pagamento buscaPelaId(Integer pagamentoId) {
		Pagamento pagamento = manager.find(Pagamento.class,pagamentoId);
		return pagamento;
	}
	
	
	public List<Pagamento> todasFormasPagamentos() {
	    System.out.println("[INFO] Consultando todas as formas de pagamentos");
		return manager.createQuery("select p from Pagamento p",Pagamento.class).getResultList();
	}
	
	

	
}
