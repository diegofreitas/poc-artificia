package br.com.artificia.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.artificia.domain.model.pedido.Pedido;
import br.com.artificia.domain.service.IPedidoDomainService;

@Service
public class PedidoService implements IPedidoService{

	@Autowired
	private IPedidoDomainService pedidoDomainService;
	
	public long iniciarPedido(long idConsultora) {
		return pedidoDomainService.iniciarPedido(idConsultora);
	}

	public Pedido carregarPedido(long idPedido) {
		return pedidoDomainService.carregarPedido(idPedido);
	}

}
