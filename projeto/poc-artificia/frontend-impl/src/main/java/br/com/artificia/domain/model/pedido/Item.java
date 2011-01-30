package br.com.artificia.domain.model.pedido;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.artificia.domain.model.estoque.IProduto;
import br.com.artificia.domain.model.estoque.Produto;
import br.com.artificia.infrastructure.IOriginator;

@Entity
class Item implements IOriginator<ItemMemento>{

	@Id
	@GeneratedValue
	private long id;
	private double total;
	private int quantidade;
	@ManyToOne(targetEntity=Produto.class,cascade=CascadeType.MERGE)
	private IProduto produto;

	Item() {
	}

	public Item(IProduto produto, int quantidade) {
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

	public IProduto produto() {
		return this.produto;
	}

	public int quantidade() {
		return quantidade;
	}
	
	public ItemMemento createMemento(){
		return new ItemMemento(produto.descricao(),this.quantidade,this.pontuacao(),this.total);
	}

}
