package br.com.artificia.domain.model.estoque;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import br.com.artificia.infrastructure.IBuilder;


@Entity
@Configurable
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private BigDecimal preco;
	
	@Embedded
	private Estoque estoque;

	private BigInteger pontos;
	
	@Autowired
	@Transient
	private IProdutoRepository produtoRepository;
	
	Produto(){}

	public Produto(Builder builder) {
		this.preco = builder.preco;
		this.estoque = builder.estoque;
		this.pontos = builder.pontos;
	}
	

	public BigDecimal preco() {
		return preco;
	}

	
	public static class Builder implements IBuilder<Produto> {
		
		private BigDecimal preco = BigDecimal.valueOf(0);
		private Estoque estoque = new Estoque(10000,0);
		private BigInteger pontos = BigInteger.valueOf(0);

		public Produto build() {
			return new Produto(this);
		}
		
		public Builder preco(double preco) {
			this.preco = BigDecimal.valueOf(preco);
			return this;
		}

		public Builder estoque(Estoque estoque) {
			this.estoque =estoque;
			return this;
		}

		public Builder pontos(int pontos) {
			this.pontos = BigInteger.valueOf(pontos);
			return this;
		}

		

	}

	/*public void reduzirEstoqueEm(BigInteger quantidade) {
		if(this.estoque.isEstoqueDisponivel(quantidade)){
			this.estoque = this.estoque.subtract(quantidade);
		}else{
			throw new EstoqueIndisponivelException();
		}
	}*/


	public BigInteger pontos() {
		return this.pontos;
	}

	public void reservarEstoqueEm(BigInteger quantidade) {
		this.estoque.reservarEm(quantidade);
	}

	public void reduzirEstoqueEm(BigInteger one) {
		this.estoque.reduzirEm(one);
	}	

}
