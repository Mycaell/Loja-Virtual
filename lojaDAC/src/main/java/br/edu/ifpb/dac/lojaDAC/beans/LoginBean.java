package br.edu.ifpb.dac.lojaDAC.beans;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.dac.lojaDAC.modelo.TipoPapeis;
import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;
import br.edu.ifpb.dac.lojaDAC.servico.identitystore.UsuarioPrincipal;

@Named
@RequestScoped
public class LoginBean {

	private Usuario usuario;
		
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ExternalContext externalContext;
	
	@Inject
	private SecurityContext securityContext;
	
//	@PostConstruct
//	public void init() {
//		usuario = new Usuario();
//		
//		Papel papel = new Papel();
//		papel.setNome(TipoPapeis.VISITANTE);
//		usuario.setPapel(papel);
//	}
	
	
	public LoginBean() {
		usuario = new Usuario();
	}
	
	public String login() {
		
		System.out.println("Login: "+usuario.getEmail()+" e senha: "+usuario.getSenha());
		AuthenticationStatus status = executaAutenticacao();
		System.out.println("Status: "+status);
		
		String paginaDirecionada = null;
		
		switch (status) {
		case SEND_CONTINUE:
			System.out.println("Principal: "+securityContext.getCallerPrincipal());
			if (securityContext.getCallerPrincipal()!=null) {
				facesContext.responseComplete();
			}else {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro no login",null));
				FacesContext.getCurrentInstance()
			    .getExternalContext()
			    .getFlash().setKeepMessages(true);
			}
			break;
		case SEND_FAILURE:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro no login",null));
			FacesContext.getCurrentInstance()
		    .getExternalContext()
		    .getFlash().setKeepMessages(true);
			break;
		case SUCCESS:
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Login realizado com sucesso!",null));	
			paginaDirecionada = "/produtos.xhtml?faces-redirect=true";
			break;
		case NOT_DONE:
				//NADA			
			break;
		}
		return paginaDirecionada;
	}
	
	public String logout() {
		HttpServletRequest request = (HttpServletRequest)externalContext.getRequest();
		try {
			request.logout();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		request.getSession().invalidate();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Logout realizado com sucesso!",null));
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	private AuthenticationStatus executaAutenticacao() {
		return securityContext.authenticate((HttpServletRequest)externalContext.getRequest(), 
				(HttpServletResponse) externalContext.getResponse(), AuthenticationParameters
				.withParams().credential(new UsernamePasswordCredential(usuario.getEmail(), usuario.getSenha())));
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Usuario getUsuarioLogado() {
		Usuario nomeUsuarioLogado = null;
		Optional<UsuarioPrincipal> usuarioLogado = securityContext.getPrincipalsByType(UsuarioPrincipal.class).stream().findFirst();
		if (usuarioLogado.isPresent()) {
			nomeUsuarioLogado = usuarioLogado.get().getUsuario();
		}
		
		return nomeUsuarioLogado;
	}
	
	public boolean isAdmin() {
		Usuario u = getUsuarioLogado();
		
		if(u != null) {
			if(u.getPapel().getNome() == TipoPapeis.ADMIN)
				return true;
		}
		
		return false;
	}
	
	public boolean isLogado() {
		Usuario u = getUsuarioLogado();
		
		if(u != null)
			return true;
		
		return false;
	}
	
	
	
	
}
