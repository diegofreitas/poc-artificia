package br.com.artificia.interfaces.web.actions;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.artificia.interfaces.facade.IPedidoServiceFacade;

public class IniciarPedioActionTest {

	private IniciarPedidoAction action;
	private Map<String, Object> sessionMap;
	private IPedidoServiceFacade serviceFacade;
	
	@Before
	public void setUp(){
		action = new IniciarPedidoAction();
		sessionMap = new HashMap<String, Object>();
		action.setSession(sessionMap);
		serviceFacade = Mockito.mock(IPedidoServiceFacade.class);
		ReflectionTestUtils.setField(action, "pedidoServiceFacade", serviceFacade);
	}

	@Test
	public void deveColocarIdPedidoNaSessionAposIniciarPedido() {
		Mockito.when(serviceFacade.iniciarPedido(0l)).thenReturn(1l);
		assertEquals(IniciarPedidoAction.SUCCESS,action.execute());
		Mockito.verify(serviceFacade, Mockito.only()).iniciarPedido(0l);
		assertEquals(1l,((Map) ReflectionTestUtils.getField(action, "sessionMap")).get(WebConstants.ID_PEDIDO_SESSION.toString()));
	}

}
