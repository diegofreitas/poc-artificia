package br.com.artificia.domain.model.pedido;

public interface IPedidoRepository {

	long save(Pedido pedido);

	Pedido findById(long idPedido);

	void update(Pedido pedido);

}
