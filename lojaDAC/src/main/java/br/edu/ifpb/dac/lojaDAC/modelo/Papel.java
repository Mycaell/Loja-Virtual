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
public class Papel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
	private TipoPapeis nome;
	
	@OneToMany(mappedBy = "papel")
	private List<Usuario> pessoas;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public TipoPapeis getNome() {
		return nome;
	}
	public void setNome(TipoPapeis nome) {
		this.nome = nome;
	}
	public List<Usuario> getPessoas() {
		return pessoas;
	}
	public void setPessoas(List<Usuario> pessoas) {
		this.pessoas = pessoas;
	}
	
	
	
	
}
