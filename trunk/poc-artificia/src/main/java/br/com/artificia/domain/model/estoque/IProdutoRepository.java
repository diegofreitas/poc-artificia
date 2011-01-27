package br.com.artificia.domain.model.estoque;


public interface IProdutoRepository {

	/*void add(Produto produto);
	
	Collection<Produto> list();*/

	Produto findById(long produtoId);
}
