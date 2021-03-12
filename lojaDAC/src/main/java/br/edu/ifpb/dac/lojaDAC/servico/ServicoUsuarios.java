package br.edu.ifpb.dac.lojaDAC.servico;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;

// adicionar validação de acordo com o papel (nível de acesso)
@Stateless
public class ServicoUsuarios {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
	    System.out.println("[INFO] UsuarioDao foi criado.");
	}
	
	public void salva(Usuario usuario) {
		
		usuario.setSenha(transformaSenhaEmHash(usuario.getSenha()));
		
		manager.merge(usuario);
		System.out.println("[INFO] Salvou o Usuario " + usuario.getNome());
	}
	public void deletar(Usuario usuario) {
		
		manager.remove(manager.find(Usuario.class, usuario.getId()));
		System.out.println("[INFO] Deletou o Usuario " + usuario.getNome());
	}
	
	private String transformaSenhaEmHash(String senhaBruta) {
		System.out.println("Gerando Hash usando Bcrypt");
		
		String salto = BCrypt.gensalt(); //salto aleatório
		String senhaHashed = BCrypt.hashpw(senhaBruta, salto); //hash da senha usando o salto aleatório
	    
	    System.out.println("Bcrypt - senhaHash: "+senhaHashed);
		
		return senhaHashed;
	}
	
	private boolean verificaSenhaHash(String senha, String senhaRecuperada) {
		return BCrypt.checkpw(senha, senhaRecuperada);
		//verificando se a senha String é igual a senha gravada usando hash - utilizando o Algotimo - BCript
	}

	public Usuario buscaPeloEmailESenha(String email, String senha) {
	    System.out.println("[INFO] Consultando o usuario pelo e-mail: "+email);
	    
	    Usuario usuarioRecuperado = null; 
	    try {
	    	
	    
	    usuarioRecuperado = manager.createQuery("select u from Usuario u where u.email = :email",Usuario.class)
		.setParameter("email", email)
		.getSingleResult();
	    
	    
	    System.out.println("Recuperou: "+usuarioRecuperado.getNome());
	    
	    }catch (NoResultException nre){
	    	return null;
	    }
	    
	    if (usuarioRecuperado!=null) {
			if(verificaSenhaHash(senha, usuarioRecuperado.getSenha())) {
				System.out.println("senha compativel para "+usuarioRecuperado.getNome());
				return usuarioRecuperado;
			}
			
		}
	    System.out.println("senha incompativel para "+usuarioRecuperado.getNome());
		return null; 
	}

    
	public Usuario buscaPelaId(Integer usuarioId) {
	    System.out.println("[INFO] Consultando Usuario pelo id: "+usuarioId);

		Usuario usuario = manager.find(Usuario.class,usuarioId);
		
		return usuario;
	}
	
	public List<Usuario> todosUsuarios() {
	    System.out.println("[INFO] Consultando todos os usuários ");
		return manager.createQuery("select u from Usuario u",Usuario.class).getResultList();
	}
	
	

	
}
