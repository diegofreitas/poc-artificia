package br.com.artificia.domain.model.pedido;

public class ItemMemento {

	private String descricaoProduto;
	private int quantidade;
	private int pontuacao;
	private double total;
	


	public ItemMemento(String descricaoProduto, int quantidade, int pontuacao,
			double total) {
		this.descricaoProduto = descricaoProduto;
		this.quantidade = quantidade;
		this.pontuacao = pontuacao;
		this.total = total;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getTotal() {
		return total;
	}

}
