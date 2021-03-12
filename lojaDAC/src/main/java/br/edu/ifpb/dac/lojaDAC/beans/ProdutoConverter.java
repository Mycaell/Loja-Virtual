package br.edu.ifpb.dac.lojaDAC.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.dac.lojaDAC.modelo.Produto;
import br.edu.ifpb.dac.lojaDAC.servico.ServicoProdutos;

//Essa é a forma indicada. A injeção de dependência do EJB não acontece
//https://stackoverflow.com/questions/8459903/creating-master-detail-pages-for-entities-how-to-link-them-and-which-bean-scope
//http://fritzthecat-blog.blogspot.com/2019/09/jsf-master-detail-example-with-jpa.html
//@Named
//@RequestScoped

//Essas alterações funcionaram:
//https://stackoverflow.com/questions/45682309/changing-faces-config-xml-from-2-2-to-2-3-causes-javax-el-propertynotfoundexcept
//https://stackoverflow.com/questions/52511992/injection-in-a-converter-does-not-work-in-jsf-2-3

@FacesConverter(managed = true, forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto>{
	
	@Inject
	private ServicoProdutos servico;

	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String idProduto) {
		if (idProduto == null || idProduto.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idProduto);
			System.out.println("ProdutoConverter (pegando como objeto) - id: "+idProduto);
			return servico.buscaPelaId(id);
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Produto com ID inválido."),e);
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto produto) {
		if (produto == null) {
			return "";
		}
		
		System.out.println("ProdutoConverter (pegando como String) - id:"+produto.getId());
		
		if (produto.getId() != null) {
			return produto.getId().toString();
		}else {
			throw new ConverterException(new FacesMessage("Produto com ID inválido."),null);
		}

	}

}
