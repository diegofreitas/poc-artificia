package br.com.artificia.interfaces.facade;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.artificia.application.IPedidoService;
import br.com.artificia.domain.model.pedido.Pedido;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

public class PedidoServiceFacade implements IPedidoServiceFacade {

	@Autowired
	private IPedidoService<Pedido> pedidoService;
	
	@Autowired
	private PedidoDTOAssembler pedidoDTOAssembler;
	
	@Override
	public void adicionarProduto(long idPedido, int codigoProduto, int quantidade) {
		pedidoService.adicionarProduto(idPedido, codigoProduto, quantidade);
	}

	@Override
	public PedidoDTO carregarPedido(long idPedido) {
		Pedido pedido = pedidoService.carregarPedido(idPedido);
		return pedidoDTOAssembler.toDTO(pedido);
	}

	@Override
	public PedidoDTO finalizarPedido(Long idPedido) {
		Pedido pedido = pedidoService.finalizarPedido(idPedido);
		return pedidoDTOAssembler.toDTO(pedido);
	}

	@Override
	public long iniciarPedido(long idConsultora) {
		return pedidoService.iniciarPedido(idConsultora);
	}

}
