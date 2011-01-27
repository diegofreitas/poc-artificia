package br.com.artificia.interfaces.web.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import br.com.artificia.application.IPedidoService;
import br.com.artificia.domain.model.pedido.Pedido;

public class PedidoAction extends ActionSupport implements SessionAware, ModelDriven{
	
	
	@Autowired
	private IPedidoService pedidoService ;
	
	private Map<String, Object> sessionMap;

	private Pedido pedido;
	
	public String execute() {	
		Object object = this.sessionMap.get("poc-artificia.id_pedido");
		pedido = pedidoService.carregarPedido((Long)object);
		return SUCCESS;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Object getModel() {
		if(pedido == null){
			return null;
		}
		return pedido.getMemento();
	}

}
