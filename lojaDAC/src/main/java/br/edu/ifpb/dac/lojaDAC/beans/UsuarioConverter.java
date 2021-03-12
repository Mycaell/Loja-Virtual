package br.edu.ifpb.dac.lojaDAC.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.dac.lojaDAC.modelo.Usuario;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoUsuarios;

//Essa é a forma indicada. A injeção de dependência do EJB não acontece
//https://stackoverflow.com/questions/8459903/creating-master-detail-pages-for-entities-how-to-link-them-and-which-bean-scope
//http://fritzthecat-blog.blogspot.com/2019/09/jsf-master-detail-example-with-jpa.html
//@Named
//@RequestScoped

//Essas alterações funcionaram:
//https://stackoverflow.com/questions/45682309/changing-faces-config-xml-from-2-2-to-2-3-causes-javax-el-propertynotfoundexcept
//https://stackoverflow.com/questions/52511992/injection-in-a-converter-does-not-work-in-jsf-2-3

@FacesConverter(managed = true, forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario>{
	
	@Inject
	private ServicoUsuarios servico;

	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String idUsuario) {
		if (idUsuario == null || idUsuario.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idUsuario);
			System.out.println("UsuárioConverter - id: "+idUsuario);
			return servico.buscaPelaId(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("usuário com ID inválido."),e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario usuario) {
		if (usuario == null) {
			return "";
		}
		if (usuario.getId() != null) {
			return usuario.getId().toString();
		}else {
			throw new ConverterException(new FacesMessage("Usuário com ID inválido."),null);
		}

	}

}
