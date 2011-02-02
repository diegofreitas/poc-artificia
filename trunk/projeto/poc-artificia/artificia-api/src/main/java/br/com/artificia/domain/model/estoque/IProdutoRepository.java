package br.com.artificia.domain.model.estoque;


public interface IProdutoRepository<T> {

	T findById(long produtoId);

	void update(T produto);
}
