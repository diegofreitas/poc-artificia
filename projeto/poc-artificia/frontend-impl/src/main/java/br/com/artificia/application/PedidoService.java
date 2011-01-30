package br.com.artificia.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.consultora.IConsultora;
import br.com.artificia.domain.model.consultora.IConsultoraRepository;
import br.com.artificia.domain.model.estoque.IProduto;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.pedido.IPedido;
import br.com.artificia.domain.model.pedido.IPedidoRepository;
import br.com.artificia.domain.model.pedido.Pedido;

@Service
public class PedidoService implements IPedidoService{

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	private IConsultoraRepository consultoraRepository;
	
	@Autowired
	private IProdutoRepository produtoRepository;

	@Transactional
	public long iniciarPedido(long idConsultora) {
		IConsultora consultora = consultoraRepository.findById(idConsultora);
		IPedido pedido = new Pedido.Builder().consultora(consultora).build(); 
		return pedidoRepository.save(pedido);
	}

	@Transactional(readOnly=true)
	public IPedido carregarPedido(long idPedido) {
		return pedidoRepository.findById(idPedido);
	}

	@Transactional
	public void adicionarProduto(long idPedido, int codigoProduto, int quantidade) {
		IPedido pedido = pedidoRepository.findById(idPedido);
		IProduto produto = produtoRepository.findById(idPedido);
		
		pedido.adiciconarProdutos(produto, quantidade);
		
		pedidoRepository.update(pedido);
		produtoRepository.update(produto);
	}

	@Transactional
	public IPedido finalizarPedido(Long idPedido) {
		IPedido pedido = pedidoRepository.findById(idPedido);
		pedido.finalizar();
		pedidoRepository.update(pedido);
		return pedido;
	}

}


