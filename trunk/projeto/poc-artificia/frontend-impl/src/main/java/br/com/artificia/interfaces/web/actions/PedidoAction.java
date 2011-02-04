package br.com.artificia.interfaces.web.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.artificia.application.IPedidoService;
import br.com.artificia.domain.model.pedido.Pedido;
import br.com.artificia.infrastructure.IOriginator;
import br.com.artificia.interfaces.facade.IPedidoServiceFacade;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PedidoAction extends ActionSupport implements SessionAware, ModelDriven<PedidoDTO>{
	
	
	@Autowired
	private IPedidoServiceFacade pedidoServiceFacade ;
	
	private Map<String, Object> sessionMap;

	private PedidoDTO pedido;
	
	public String execute() {	
		Long idPedido = (Long) this.sessionMap.get(WebConstants.ID_PEDIDO_SESSION.toString());
		pedido = pedidoServiceFacade.carregarPedido(idPedido);
		return SUCCESS;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


	public PedidoDTO getModel() {
		return pedido;
	}

}
