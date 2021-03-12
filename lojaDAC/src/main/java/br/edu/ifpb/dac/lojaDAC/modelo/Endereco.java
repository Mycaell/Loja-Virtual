package br.edu.ifpb.dac.lojaDAC.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String estado;
	private String cidade;
	private String CEP;
	private String bairro;
	private String rua;
	private String num;
	private String complemento;
	
	
	
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Endereco(String estado, String cidade, String CEP, String bairro, String rua, String num, String complemento) {
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.CEP = CEP;
		this.bairro = bairro;
		this.rua = rua;
		this.num = num;
		this.complemento = complemento;
	}



	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	
	
	
}
