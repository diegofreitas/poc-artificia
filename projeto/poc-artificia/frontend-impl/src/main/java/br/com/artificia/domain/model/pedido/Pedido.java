package br.com.artificia.domain.model.pedido;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.estoque.Produto;
import br.com.artificia.infrastructure.IBuilder;
import br.com.artificia.infrastructure.IOriginator;

@Entity
public class Pedido{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Collection<Item> itens;
	private double total;
	private int pontuacao;
	private SituacaoPedido situacao = SituacaoPedido.INICIADO;
	@ManyToOne
	private Consultora consultora;

	Pedido(){ }
	
	public Pedido(Builder builder) {
		this.itens = builder.itens;
		this.total = builder.total;
		this.pontuacao = builder.pontuacao;
		this.consultora = builder.consultora;
	}
	
	public Long id() {
		return id;
	}



	private void aumentarPontuacaoEm(int pontos) {
		if(isPontuacaoExcederCom(pontos)){
			throw new PontuacaoMaximaExcedidaException();
		}else{
			this.pontuacao += pontos;
		}
	}

	private boolean isPontuacaoExcederCom(int pontos) {
		if(this.pontuacao + pontos > this.consultora.pontuacaoMaxima()){
			return true;
		}
		return false;
	}

	private void adicionarItem(Item novoItem) {
		itens.add(novoItem);
		total += novoItem.total();
	}

	public double total() {
		return total;
	}

	public static class Builder implements IBuilder<Pedido> {

		private Collection<Item> itens = new HashSet<Item>();
		private double total = 0;
		private int pontuacao = 0;
		private Consultora consultora ;

		public Pedido build() {
			if(consultora == null){
				throw new IllegalStateException();
			}
			return new Pedido(this);
		}
		
		public Builder consultora(Consultora consultora) {
			this.consultora  = consultora;
			return this;
		}

	}

	public void finalizar() {
		this.situacao = SituacaoPedido.FINALIZADO;	
		CollectionUtils.forAllDo(this.itens, new Closure() {
			public void execute(Object input) {
				Item item = (Item)input;
				item.produto().reduzirEstoqueEm(item.quantidade());
			}
		});
	}

	public void adiciconarProdutos(Produto produto, int quantidade) {
		produto.reservarEstoqueEm(quantidade);
		Item novoItem = new Item(produto, quantidade);
		this.aumentarPontuacaoEm(novoItem.pontuacao());
		this.adicionarItem(novoItem);
	}

	/*@Override
	public PedidoMemento createMemento() {
		Collection<ItemDTO> itensMemento = new ArrayList<ItemDTO>();
		for(Item item: this.itens){
			itensMemento.add(item.createMemento());
		}
		PedidoMemento memento = new PedidoMemento(
				this.id,
				this.total, 
				this.pontuacao,
				itensMemento,
				this.situacao
		);

		return memento;
	}*/

}
