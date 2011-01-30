package br.com.artificia.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.artificia.domain.model.estoque.IProduto;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;

@Repository
public class ProdutoRepository implements IProdutoRepository {

	@PersistenceContext
	private EntityManager em;
	
	public IProduto findById(long produtoId) {
		return (IProduto) em.find(Produto.class, produtoId);
	}

	public void update(IProduto produto) {
		em.merge(produto);
	}

}
