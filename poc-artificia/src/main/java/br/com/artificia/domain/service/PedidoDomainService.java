package br.com.artificia.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.consultora.IConsultoraRepository;
import br.com.artificia.domain.model.pedido.IPedidoRepository;
import br.com.artificia.domain.model.pedido.Pedido;

@Service
public class PedidoDomainService implements IPedidoDomainService {
	
	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Autowired
	private IConsultoraRepository consultoraRepository;

	@Transactional
	public long iniciarPedido(long idConsultora) {
		Consultora consultora = consultoraRepository.findById(idConsultora);
		Pedido pedido = new Pedido.Builder().consultora(consultora).build(); 
		return pedidoRepository.save(pedido);
	}

	public Pedido carregarPedido(long idPedido) {
		return pedidoRepository.findById(idPedido);
	}

}



