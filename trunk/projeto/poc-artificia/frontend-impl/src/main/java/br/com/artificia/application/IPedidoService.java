package br.com.artificia.application;

import br.com.artificia.domain.model.pedido.Pedido;

public interface IPedidoService {

	long iniciarPedido(long idConsultora);

	Pedido carregarPedido(long object);

	void adicionarProduto(long pedido, int codigoProduto, int quantidade);

	Pedido finalizarPedido(Long idPedido);
}
