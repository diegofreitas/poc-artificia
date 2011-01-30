package br.com.artificia.interfaces.web.actions;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.artificia.application.IPedidoService;
import br.com.artificia.domain.model.pedido.PedidoMemento;
import br.com.artificia.infrastructure.IOriginator;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PedidoAction extends ActionSupport implements SessionAware, ModelDriven<PedidoMemento>{
	
	
	@Autowired
	private IPedidoService pedidoService ;
	
	private Map<String, Object> sessionMap;

	private IOriginator<PedidoMemento> pedido;
	
	public String execute() {	
		Long idPedido = (Long) this.sessionMap.get("poc-artificia.id_pedido");
		pedido = pedidoService.carregarPedido(idPedido);
		return SUCCESS;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public IOriginator<PedidoMemento> getPedido() {
		return pedido;
	}

	public PedidoMemento getModel() {
		if(pedido == null){
			return null;
		}
		return pedido.createMemento();
	}

}
