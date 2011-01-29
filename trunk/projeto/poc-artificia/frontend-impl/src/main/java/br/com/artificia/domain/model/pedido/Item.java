package br.com.artificia.domain.model.pedido;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.artificia.domain.model.estoque.Produto;

@Entity
class Item {

	@Id
	@GeneratedValue
	private long id;
	private BigDecimal total;
	private BigInteger quantidade;
	@ManyToOne(cascade=CascadeType.MERGE)
	private Produto produto;

	Item() {
	}

	public Item(Produto produto, BigInteger quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		total = produto.preco().multiply(
				BigDecimal.valueOf(quantidade.intValue()));
	}

	public BigDecimal total() {
		return total;
	}

	public BigInteger pontuacao() {
		return this.produto.pontos().multiply(this.quantidade);
	}

	public Produto produto() {
		return this.produto;
	}

	public BigInteger quantidade() {
		return quantidade;
	}
	
	public ItemMemento createMemento(){
		return new ItemMemento(produto.descricao(),this.quantidade.intValue(),this.pontuacao().intValue(),this.total.doubleValue());
	}

}
