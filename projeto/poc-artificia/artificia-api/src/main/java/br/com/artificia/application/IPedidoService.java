package br.com.artificia.application;

import br.com.artificia.domain.model.pedido.IPedido;

public interface IPedidoService {

	long iniciarPedido(long idConsultora);

	IPedido carregarPedido(long idPedido);

	void adicionarProduto(long idPedido, int codigoProduto, int quantidade);

	IPedido finalizarPedido(Long idPedido);
}
