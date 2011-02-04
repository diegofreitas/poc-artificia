package br.com.artificia.interfaces.facade;

import org.springframework.stereotype.Component;

import br.com.artificia.domain.model.pedido.Pedido;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

@Component
public class PedidoDTOAssembler {

	public PedidoDTO toDTO(Pedido pedido) {
		return new PedidoDTO();
	}

}
