package br.com.artificia.domain.model.pedido;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.estoque.Produto;
import br.com.artificia.infrastructure.IBuilder;

@Entity
@Configurable(dependencyCheck=true)
public class Pedido {

	public enum SituacaoPedido {
		INICIADO, FINALIZADO
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Collection<Item> itens;
	private BigDecimal total;
	private BigInteger pontuacao;
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
	
	public BigInteger pontuacao() {
		return this.pontuacao;
	}
	
	public Long id() {
		return id;
	}

	public void adiciconarProdutos(Produto produto, BigInteger quantidade) {
		produto.reservarEstoqueEm(quantidade);
		Item novoItem = new Item(produto, quantidade);
		this.aumentarPontuacaoEm(novoItem.pontuacao());
		this.adicionarItem(novoItem);
	}

	private void aumentarPontuacaoEm(BigInteger pontos) {
		if(isPontuacaoExcederCom(pontos)){
			throw new PontuacaoMaximaExcedidaException();
		}else{
			this.pontuacao = this.pontuacao.add(pontos);
		}
	}

	private boolean isPontuacaoExcederCom(BigInteger pontos) {
		if(this.pontuacao.add(pontos).intValue() > this.consultora.pontuacaoMaxima().intValue()){
			return true;
		}
		return false;
	}

	private void adicionarItem(Item novoItem) {
		itens.add(novoItem);
		total = total.add(novoItem.total());
	}

	public BigDecimal total() {
		return total;
	}

	public static class Builder implements IBuilder<Pedido> {

		private Collection<Item> itens = new HashSet<Item>();
		private BigDecimal total = BigDecimal.ZERO;
		private BigInteger pontuacao = BigInteger.ZERO;
		private Consultora consultora = Consultora.NULL_CONSULTORA;

		public Pedido build() {
			if(consultora == null || Consultora.NULL_CONSULTORA.equals(consultora)){
				throw new IllegalStateException();
			}
			return new Pedido(this);
		}

		/*public Builder itens(Collection<Item> itens) {
			this.itens = itens;
			return this;
		}

		public Builder pontuacao(int pontuacao) {
			this.pontuacao = BigInteger.valueOf(pontuacao);
			return this;
		}*/
		
		public Builder consultora(Consultora consultora) {
			this.consultora  = consultora;
			return this;
		}

	}

	
	@Transactional
	public void finalizar() {
		this.situacao = SituacaoPedido.FINALIZADO;	
		CollectionUtils.forAllDo(this.itens, new Closure() {
			public void execute(Object input) {
				Item item = (Item)input;
				item.produto().reduzirEstoqueEm(item.quantidade());
			}
		});
	}

	public Object getMemento() {
		PedidoMemento memento = new PedidoMemento(
				this.id,
				this.total.doubleValue(), 
				this.pontuacao.intValue()
		);

		return memento;
	}

}
