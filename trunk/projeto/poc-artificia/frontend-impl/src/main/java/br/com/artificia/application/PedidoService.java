package br.com.artificia.application;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.consultora.IConsultoraRepository;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;
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
		Consultora consultora = consultoraRepository.findById(idConsultora);
		Pedido pedido = new Pedido.Builder().consultora(consultora).build(); 
		return pedidoRepository.save(pedido);
	}

	@Transactional(readOnly=true)
	public Pedido carregarPedido(long idPedido) {
		return pedidoRepository.findById(idPedido);
	}

	@Transactional
	public void adicionarProduto(long idPedido, int codigoProduto, int quantidade) {
		Pedido pedido = pedidoRepository.findById(idPedido);
		Produto produto = produtoRepository.findById(idPedido);
		
		pedido.adiciconarProdutos(produto, quantidade);
		
		pedidoRepository.update(pedido);
		produtoRepository.update(produto);
	}

	@Transactional
	public Pedido finalizarPedido(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido);
		pedido.finalizar();
		pedidoRepository.update(pedido);
		return pedido;
	}

}


