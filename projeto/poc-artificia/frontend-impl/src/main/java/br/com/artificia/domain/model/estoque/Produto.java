package br.com.artificia.domain.model.estoque;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;

import br.com.artificia.infrastructure.IBuilder;


@Entity
@Configurable
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double preco;
	
	@Embedded
	private Estoque estoque;

	private int pontos;

	private String descricao;

	
	Produto(){}

	public Produto(Builder builder) {
		this.preco = builder.preco;
		this.estoque = builder.estoque;
		this.pontos = builder.pontos;
	}
	

	public double preco() {
		return preco;
	}

	
	public static class Builder implements IBuilder<Produto> {
		
		private double preco = 0;
		private Estoque estoque = new Estoque(10000,0);
		private int pontos = 0;

		public Produto build() {
			return new Produto(this);
		}
		
		public Builder preco(double preco) {
			this.preco =  preco ;
			return this;
		}

		public Builder estoque(Estoque estoque) {
			this.estoque =estoque;
			return this;
		}

		public Builder pontos(int pontos) {
			this.pontos =  pontos ;
			return this;
		}

		

	}

	public int pontos() {
		return this.pontos;
	}

	public void reservarEstoqueEm(int quantidade) {
		this.estoque.reservarEm(quantidade);
	}

	public void reduzirEstoqueEm(int one) {
		this.estoque.reduzirEm(one);
	}

	public String descricao() {
		return this.descricao;
	}	

}
