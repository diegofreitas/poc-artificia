package br.com.artificia.domain.model.estoque;

public interface IProduto {

	void reservarEstoqueEm(int quantidade);

	double preco();

	int pontos();

	String descricao();

	void reduzirEstoqueEm(int quantidade);

}
