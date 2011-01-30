package br.com.artificia.interfaces.web.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.artificia.application.IPedidoService;

import com.opensymphony.xwork2.ActionSupport;

public class IniciarPedidoAction extends ActionSupport implements SessionAware{
	
	
	@Autowired
	private IPedidoService pedidoService ;
	
	private Map<String, Object> sessionMap;
	
	@Action(
		results={@Result(type="redirect",location="pedido.action")}
	)
	public String execute() {	
		this.sessionMap.put("poc-artificia.id_pedido", pedidoService.iniciarPedido(0));
		return SUCCESS;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}


}
