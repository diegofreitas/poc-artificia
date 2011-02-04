package br.com.artificia.interfaces.facade;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.pedido.Pedido;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

public class PedidoDTOAssemblerTest {

	@Test
	public void testToDTO() {
		Pedido pedido = new Pedido.Builder()
		.consultora(new Consultora(1, 100, "teste"))
		.build();
		PedidoDTO pedidoDTO = new PedidoDTOAssembler().toDTO(pedido);
		
	}

}
