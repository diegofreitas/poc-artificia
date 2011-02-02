package br.com.artificia.domain.model.pedido;

public interface IPedidoRepository<P> {

	long save(P pedido);

	P findById(long idPedido);

	void update(P pedido);

}
