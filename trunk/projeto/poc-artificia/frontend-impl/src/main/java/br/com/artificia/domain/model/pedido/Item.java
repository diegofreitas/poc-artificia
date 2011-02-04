package br.com.artificia.domain.model.pedido;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.artificia.domain.model.estoque.Produto;
import br.com.artificia.infrastructure.IOriginator;

@Entity
class Item implements IOriginator<ItemDTO>{

	@Id
	@GeneratedValue
	private long id;
	private double total;
	private int quantidade;
	@ManyToOne
	private Produto produto;

	Item() {
	}

	public Item(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
		total = produto.preco() * quantidade ;
	}

	public double total() {
		return total;
	}

	public int pontuacao() {
		return this.produto.pontos() * this.quantidade;
	}

	public Produto produto() {
		return this.produto;
	}

	public int quantidade() {
		return quantidade;
	}
	
	public ItemDTO createMemento(){
		return new ItemDTO(produto.descricao(),this.quantidade,this.pontuacao(),this.total);
	}

}
