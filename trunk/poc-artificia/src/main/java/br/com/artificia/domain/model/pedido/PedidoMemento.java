package br.com.artificia.domain.model.pedido;

public class PedidoMemento {

	private double total;
	private int pontuacao;

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public int getPontuacao() {
		return pontuacao;
	}
}
