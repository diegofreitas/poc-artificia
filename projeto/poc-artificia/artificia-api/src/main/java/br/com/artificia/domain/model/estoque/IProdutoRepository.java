package br.com.artificia.domain.model.estoque;


public interface IProdutoRepository {

	IProduto findById(long produtoId);

	void update(IProduto produto);
}
