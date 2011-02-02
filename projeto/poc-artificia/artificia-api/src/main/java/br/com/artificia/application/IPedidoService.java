package br.com.artificia.application;


public interface IPedidoService<P> {

	long iniciarPedido(long idConsultora);

	P carregarPedido(long idPedido);

	void adicionarProduto(long idPedido, int codigoProduto, int quantidade);

	P finalizarPedido(Long idPedido);
}
