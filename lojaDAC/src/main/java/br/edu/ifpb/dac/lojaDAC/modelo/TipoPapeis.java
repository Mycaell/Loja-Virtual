package br.edu.ifpb.dac.lojaDAC.modelo;

public enum TipoPapeis {

	ADMIN("ADMIN"), CLIENTE("CLIENTE"), VISITANTE("VISITANTE");
	
	
	private String valor;
	
	
	TipoPapeis(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}
	
}
