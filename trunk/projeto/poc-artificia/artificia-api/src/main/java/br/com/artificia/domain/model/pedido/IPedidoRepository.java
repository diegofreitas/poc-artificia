package br.com.artificia.domain.model.pedido;

public interface IPedidoRepository {

	long save(IPedido pedido);

	IPedido findById(long idPedido);

	void update(IPedido pedido);

}
