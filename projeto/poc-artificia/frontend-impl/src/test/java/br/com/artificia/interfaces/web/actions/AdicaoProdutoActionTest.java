package br.com.artificia.interfaces.web.actions;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import br.com.artificia.interfaces.facade.IPedidoServiceFacade;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;
import br.com.artificia.interfaces.web.actions.AdicaoProdutoAction;
import br.com.artificia.interfaces.web.actions.WebConstants;

public class AdicaoProdutoActionTest {

	@Test
	public void deveDelegarAdicaoDoProdutoParaFacadeAoExecutar() {
		AdicaoProdutoAction action = new AdicaoProdutoAction();
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		sessionMap.put(WebConstants.ID_PEDIDO_SESSION.toString(),Long.valueOf(1));
		action.setSession(sessionMap);
		action.setCodigoProduto(1);
		action.setQuantidade(1);
		
		IPedidoServiceFacade serviceFacade = Mockito.mock(IPedidoServiceFacade.class);
		
		ReflectionTestUtils.setField(action, "pedidoServiceFacade", serviceFacade);
		assertEquals(AdicaoProdutoAction.SUCCESS,action.execute());
		
		Mockito.verify(serviceFacade, Mockito.only()).adicionarProduto(1l,1,1);
	}
	
	@Test
	public void deveRetornarERRORQuandoNaoTemPedidoEmAndamento() {
		AdicaoProdutoAction action = new AdicaoProdutoAction();
		Map<String, Object> sessionMap = new HashMap<String, Object>();
		action.setSession(sessionMap);//Session sem id do pedido
		action.setCodigoProduto(1);
		action.setQuantidade(1);
		
		IPedidoServiceFacade serviceFacade = Mockito.mock(IPedidoServiceFacade.class);
		ReflectionTestUtils.setField(action, "pedidoServiceFacade", serviceFacade);
		
		assertEquals(AdicaoProdutoAction.ERROR,action.execute());
		
		Mockito.verify(serviceFacade, Mockito.never()).adicionarProduto(1l,1,1);
	}
	
	@Test
	@Ignore("Exemplo de mais testes")
	public void testValidaQuantidadeIdProduto(){
		
	}

}
