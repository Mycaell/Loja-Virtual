package br.edu.ifpb.dac.lojaDAC.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.edu.ifpb.dac.lojaDAC.modelo.Endereco;
import br.edu.ifpb.dac.lojaDAC.modelo.Papel;
import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoPapeis;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoUsuarios;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	private boolean admin;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	
	
//	mesmo esquema do livroBean
	private Integer idPapel;

	private List<Papel> papeisUsuario;
	
	@EJB
	private ServicoUsuarios dao;
	
	
	@EJB
	private ServicoPapeis papelDao;
	
	
	public UsuarioBean() {
		
	}
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuario.setEndereco(new Endereco());
		usuarios = this.dao.todosUsuarios();
		papeisUsuario = papelDao.todosPapeis();
	}
	
	public void cadastra() {
		System.out.println("Cadastra - Usuario: "+usuario.getNome());
		usuario.setPapel(papelDao.buscaPelaId(idPapel));
		
		
		this.dao.salva(usuario);
		
//		o ideal era deixar assim, mas isso impacta em um clone temporário quando utiliza a feature de editar
//		usuarios.add(usuario);
	
		usuarios = this.dao.todosUsuarios();
		
		this.usuario = new Usuario();
		
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Salvo com sucesso!"));
	}
	
	public void deletar() {
		
		System.out.println("Deletar - Usuario: "+usuario.getNome());
		
		this.dao.deletar(usuario);
		
		usuarios.remove(usuario);
	
		this.usuario = new Usuario();
		
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Exluído com sucesso!"));
	}
	
	
	public void limpar() {
//		System.out.println("LIMPOU");
		usuario = new Usuario();
		usuario.setEndereco(new Endereco());
	}
	

	public void todosPapeis() {
		papeisUsuario = papelDao.todosPapeis();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public List<Papel> getPapeisUsuario() {
		return papeisUsuario;
	}

	public void setPapeisUsuario(List<Papel> papeisUsuario) {
		this.papeisUsuario = papeisUsuario;
	}

	public ServicoUsuarios getDao() {
		return dao;
	}

	public void setDao(ServicoUsuarios dao) {
		this.dao = dao;
	}

	public ServicoPapeis getPapelDao() {
		return papelDao;
	}

	public void setPapelDao(ServicoPapeis papelDao) {
		this.papelDao = papelDao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIdPapel() {
		return idPapel;
	}

	public void setIdPapel(Integer idPapel) {
		this.idPapel = idPapel;
	}

	
	
	
}
