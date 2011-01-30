package br.com.artificia.infrastructure.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.pedido.IPedido;
import br.com.artificia.domain.model.pedido.IPedidoRepository;
import br.com.artificia.domain.model.pedido.Pedido;

@Repository
public class PedidoRepository implements IPedidoRepository {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public long save(IPedido pedido) {
		em.persist(pedido);
		return pedido.id();
	}

	public IPedido findById(long idPedido) {
		return (IPedido)em.find(Pedido.class, idPedido);
	}

	public void update(IPedido pedido) {
		em.merge(pedido);
	}
	
	

}
