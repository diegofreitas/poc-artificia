package br.com.artificia.domain.service;

import br.com.artificia.domain.model.pedido.Pedido;

public interface IPedidoDomainService {

	long iniciarPedido(long idConsultora);

	Pedido carregarPedido(long idPedido);
}
