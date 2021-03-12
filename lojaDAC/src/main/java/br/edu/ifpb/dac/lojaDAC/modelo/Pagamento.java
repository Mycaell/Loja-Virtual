package br.edu.ifpb.dac.lojaDAC.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;
	
	@OneToMany(mappedBy = "pagamento")
	private List<Compra> compras;
	
	public Pagamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pagamento(TipoPagamento tipo) {
		tipoPagamento = tipo;
	}
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	
	public void setTipoPagamento(TipoPagamento tipo) {
		this.tipoPagamento = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	
	
	
}
