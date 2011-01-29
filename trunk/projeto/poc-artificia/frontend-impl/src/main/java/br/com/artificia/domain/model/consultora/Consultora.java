package br.com.artificia.domain.model.consultora;

import java.math.BigInteger;

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
	private BigInteger pontuacaoMaxima;
	private String nome;

	public Consultora(Builder builder) {
		this.pontuacaoMaxima = builder.pontuacaoMaxima;
	}

	Consultora() {
		// TODO Auto-generated constructor stub
	
	}

	public BigInteger pontuacaoMaxima() {
		return pontuacaoMaxima;
	}

	public String nome(){
		return nome;
	}
	
	public static class Builder implements IBuilder<Consultora>{
		
		private BigInteger pontuacaoMaxima=BigInteger.ZERO;
		
		public Consultora build() {
			return new Consultora(this);
		}
		
		public Builder pontMaxima(int max){
			this.pontuacaoMaxima = BigInteger.valueOf(max);
			return this;
		}
		
	}
}
