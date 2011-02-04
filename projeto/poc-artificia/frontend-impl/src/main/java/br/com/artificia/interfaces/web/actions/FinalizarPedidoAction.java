package br.com.artificia.interfaces.web.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.artificia.application.IPedidoService;
import br.com.artificia.domain.model.pedido.Pedido;
import br.com.artificia.interfaces.facade.IPedidoServiceFacade;
import br.com.artificia.interfaces.facade.dto.PedidoDTO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FinalizarPedidoAction extends ActionSupport implements SessionAware, ModelDriven<PedidoDTO>{
	
	
	@Autowired
	private IPedidoServiceFacade pedidoServiceFacade ;
	
	private Map<String, Object> sessionMap;

	private PedidoDTO pedido;
	
	@Action(results = { 
			@Result(name=ERROR,type = "redirect", location = "index.action")
	})
	public String execute() {	
		Long idPedido = (Long) this.sessionMap.get(WebConstants.ID_PEDIDO_SESSION.toString());
		if(idPedido == null){
			return ERROR;
		}
		pedido = pedidoServiceFacade.finalizarPedido(idPedido);
		return SUCCESS;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public PedidoDTO getModel() {
		return pedido;
	}

}
