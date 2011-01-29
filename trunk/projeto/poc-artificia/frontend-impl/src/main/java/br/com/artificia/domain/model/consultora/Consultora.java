package br.com.artificia.domain.model.consultora;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.artificia.infrastructure.IBuilder;

@Entity
public class Consultora {
	
	public static final Consultora NULL_CONSULTORA = new Consultora.Builder().build();
	
	@Id
	@GeneratedValue
	private long id;
	private int pontuacaoMaxima;
	private String nome;

	public Consultora(Builder builder) {
		this.pontuacaoMaxima = builder.pontuacaoMaxima;
	}

	Consultora() {
		// TODO Auto-generated constructor stub
	
	}

	public int pontuacaoMaxima() {
		return pontuacaoMaxima;
	}

	public String nome(){
		return nome;
	}
	
	public static class Builder implements IBuilder<Consultora>{
		
		private int pontuacaoMaxima= 0;
		
		public Consultora build() {
			return new Consultora(this);
		}
		
		public Builder pontMaxima(int max){
			this.pontuacaoMaxima =  max;
			return this;
		}
		
	}
}
