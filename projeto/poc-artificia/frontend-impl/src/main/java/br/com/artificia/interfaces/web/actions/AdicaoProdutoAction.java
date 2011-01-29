package br.com.artificia.interfaces.web.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.artificia.application.IPedidoService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdicaoProdutoAction extends ActionSupport implements SessionAware{
	
	
	@Autowired
	private IPedidoService pedidoService ;
	
	private int codigoProduto;
	private int quantidade;
	
	
	private Map<String, Object> sessionMap;
	
	@Action(
		results={@Result(type="redirect",location="pedido.action")}
	)
	public String execute() {	
		Long idPedido = (Long) this.sessionMap.get("poc-artificia.id_pedido");
		pedidoService.adicionarProduto(idPedido, codigoProduto,quantidade);
		return SUCCESS;
	}

	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}
