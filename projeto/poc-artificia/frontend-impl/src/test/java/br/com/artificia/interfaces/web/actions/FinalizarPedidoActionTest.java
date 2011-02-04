package br.com.artificia.interfaces.web.actions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.artificia.interfaces.facade.IPedidoServiceFacade;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

public class FinalizarPedidoActionTest {

	private FinalizarPedidoAction action;
	private Map<String, Object> sessionMap;
	private IPedidoServiceFacade serviceFacade;
	
	@Before
	public void setUp(){
		action = new FinalizarPedidoAction();
		sessionMap = new HashMap<String, Object>();
		action.setSession(sessionMap);
		serviceFacade = Mockito.mock(IPedidoServiceFacade.class);
		ReflectionTestUtils.setField(action, "pedidoServiceFacade", serviceFacade);
	}

	@Test
	public void deveDelegarFinalizacaoPedidoParaFacadeAoExecutar() {
		sessionMap.put(WebConstants.ID_PEDIDO_SESSION.toString(),Long.valueOf(1));
		PedidoDTO pedidoDTO = new PedidoDTO();
		Mockito.when(serviceFacade.finalizarPedido(1l)).thenReturn(pedidoDTO);
		assertEquals(FinalizarPedidoAction.SUCCESS,action.execute());
		Mockito.verify(serviceFacade, Mockito.only()).finalizarPedido(1l);
		assertNotNull(action.getModel());
	}
	
	@Test
	public void deveRetornarERRORAoExecutarQuandoNaoTemPedidoEmAndamento() {
		assertEquals(FinalizarPedidoAction.ERROR,action.execute());
		Mockito.verify(serviceFacade, Mockito.never()).finalizarPedido(Matchers.anyLong());
		assertNull(action.getModel());
	}


}
