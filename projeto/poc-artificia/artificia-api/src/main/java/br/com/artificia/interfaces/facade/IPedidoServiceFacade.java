package br.com.artificia.interfaces.facade;

import br.com.artificia.interfaces.facade.dto.PedidoDTO;

public interface IPedidoServiceFacade {

	long iniciarPedido(long idConsultora);
	
	PedidoDTO carregarPedido(long idPedido);

	void adicionarProduto(long idPedido, int codigoProduto, int quantidade);

	PedidoDTO finalizarPedido(Long idPedido);

}
