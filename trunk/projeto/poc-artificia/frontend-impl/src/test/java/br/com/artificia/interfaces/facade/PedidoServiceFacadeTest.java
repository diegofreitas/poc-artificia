package br.com.artificia.interfaces.facade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.artificia.application.IPedidoService;
import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.pedido.Pedido;


public class PedidoServiceFacadeTest {
	
	private IPedidoServiceFacade pedidoServiceFacade;
	private IPedidoService<Pedido> pedidoService;
	
	@Before
	public void setUp(){
		pedidoServiceFacade = new PedidoServiceFacade();
		pedidoService = Mockito.mock(IPedidoService.class);
		ReflectionTestUtils.setField(pedidoServiceFacade, "pedidoService", pedidoService);
		ReflectionTestUtils.setField(pedidoServiceFacade, "pedidoDTOAssembler", new PedidoDTOAssembler());
		
	}

	@Test
	public void deveRetornarDTOApartirDoPedidoCarrecado(){
		Pedido pedido = new Pedido.Builder()
								.consultora(new Consultora(1, 100, "teste"))
								.build();
		Mockito.when(pedidoService.carregarPedido(1l)).thenReturn(pedido);
		assertNotNull(pedidoServiceFacade.carregarPedido(1l));
		Mockito.verify(pedidoService,Mockito.only()).carregarPedido(1l);
	}

}
