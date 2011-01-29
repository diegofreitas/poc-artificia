package br.com.artificia.domain.model.pedido;

public class PedidoMemento {

	private long id;
	private double total;
	private int pontuacao;

	public PedidoMemento(long id, double total, int pontuacao) {
		this.id = id;
		this.total = total;
		this.pontuacao = pontuacao;
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
}
