package br.com.artificia.interfaces.web.actions;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.artificia.interfaces.facade.IPedidoServiceFacade;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

public class PedioActionTest {

	private PedidoAction action;
	private Map<String, Object> sessionMap;
	private IPedidoServiceFacade serviceFacade;
	
	@Before
	public void setUp(){
		action = new PedidoAction();
		sessionMap = new HashMap<String, Object>();
		action.setSession(sessionMap);
		serviceFacade = Mockito.mock(IPedidoServiceFacade.class);
		ReflectionTestUtils.setField(action, "pedidoServiceFacade", serviceFacade);
	}

	@Test
	public void deveColocarPedidoDTONoModel() {
		PedidoDTO pedidoDTO = new PedidoDTO();
		sessionMap.put(WebConstants.ID_PEDIDO_SESSION.toString(), 1l);
		Mockito.when(serviceFacade.carregarPedido(1l)).thenReturn(pedidoDTO);
		assertEquals(PedidoAction.SUCCESS,action.execute());
		Mockito.verify(serviceFacade, Mockito.only()).carregarPedido(1l);
		//assertEquals(1l,((Map) ReflectionTestUtils.getField(action, "sessionMap")).get(WebConstants.ID_PEDIDO_SESSION.toString()));
		assertNotNull(action.getModel());
	}

}
