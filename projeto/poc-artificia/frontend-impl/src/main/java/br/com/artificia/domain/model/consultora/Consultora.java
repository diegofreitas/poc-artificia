package br.com.artificia.domain.model.consultora;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Consultora  {
	
	@Id
	@GeneratedValue
	private long id;
	private int pontuacaoMaxima;
	private String nome;


	public Consultora(long id, int pontuacaoMaxima, String nome) {
		this.id = id;
		this.pontuacaoMaxima = pontuacaoMaxima;
		this.nome = nome;
	}

	Consultora() {
	
	}

	public int pontuacaoMaxima() {
		return pontuacaoMaxima;
	}
	
}
