package br.com.artificia.infrastructure.persistence;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;

@Repository
public class ProdutoRepository implements IProdutoRepository {

	@PersistenceContext
	private EntityManager em;
	
	/*@Transactional
	public void add(Produto produto) {
		em.persist(produto);
	}

	public Collection<Produto> list() {
		return em.createQuery("from Produto").getResultList();
	}*/

	public Produto findById(long produtoId) {
		return em.find(Produto.class, produtoId);
	}

	public void update(Produto produto) {
		em.merge(produto);
	}
	
	

}
