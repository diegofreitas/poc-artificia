package br.com.artificia.domain.model.pedido;

import br.com.artificia.domain.model.estoque.IProduto;
import br.com.artificia.infrastructure.IOriginator;

public interface IPedido extends IOriginator<PedidoMemento>{

	public enum SituacaoPedido {
		INICIADO, FINALIZADO
	}
	
	void adiciconarProdutos(IProduto produto, int quantidade);

	void finalizar();

	Long id();

}
