package br.com.artificia.interfaces.facade.dto;

import java.util.Collection;
import java.util.Collections;

import br.com.artificia.domain.model.pedido.ItemDTO;
import br.com.artificia.domain.model.pedido.SituacaoPedido;

public class PedidoDTO {

	private long id;
	private double total;
	private int pontuacao;
	private Collection<ItemDTO> itens = Collections.emptyList();
	private SituacaoPedido situacao; 

	public PedidoDTO(long id, double total, int pontuacao, Collection<ItemDTO> itensMemento, SituacaoPedido situacao) {
		this.id = id;
		this.total = total;
		this.pontuacao = pontuacao;
		this.itens = itensMemento;
		this.situacao = situacao;
	}
	
	public PedidoDTO() {
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

	public Collection<ItemDTO> getItens() {
		return itens;
	}

	public SituacaoPedido getSituacao() {
		return situacao;
	}
}
