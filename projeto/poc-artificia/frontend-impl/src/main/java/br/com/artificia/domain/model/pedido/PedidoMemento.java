package br.com.artificia.domain.model.pedido;

import java.util.Collection;
import java.util.Collections;

import br.com.artificia.domain.model.pedido.Pedido.SituacaoPedido;

public class PedidoMemento {

	private long id;
	private double total;
	private int pontuacao;
	private Collection<ItemMemento> itens = Collections.emptyList();
	private SituacaoPedido situacao; 

	public PedidoMemento(long id, double total, int pontuacao, Collection<ItemMemento> itensMemento, SituacaoPedido situacao) {
		this.id = id;
		this.total = total;
		this.pontuacao = pontuacao;
		this.itens = itensMemento;
		this.situacao = situacao;
	}

	public double getTotal() {
		return total;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public long getId() {
		return id;
	}

	public Collection<ItemMemento> getItens() {
		return itens;
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}
}
