package br.com.artificia.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;

@Repository
public class ProdutoRepository implements IProdutoRepository<Produto> {

	@PersistenceContext
	private EntityManager em;
	
	public Produto findById(long produtoId) {
		return em.find(Produto.class, produtoId);
	}

	public void update(Produto produto) {
		em.merge(produto);
	}

}
