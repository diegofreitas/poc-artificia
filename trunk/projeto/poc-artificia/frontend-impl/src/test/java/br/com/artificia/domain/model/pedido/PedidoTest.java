package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.getField;

import java.util.Collection;

import javax.annotation.Resource;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.consultora.IConsultora;
import br.com.artificia.domain.model.estoque.Estoque;
import br.com.artificia.domain.model.estoque.EstoqueIndisponivelException;
import br.com.artificia.domain.model.estoque.IProduto;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
public class PedidoTest {

	private static final int PEDIDO_SIMPLES = 1000;
	private static final long PRODUTO_CREME_ID = 1;

	@Autowired
	private IProdutoRepository produtoRepository;

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Resource(name="dbtester")
	private IDatabaseTester databaseTester;

	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass()
				.getResourceAsStream("/dbunit/PedidoRepository.xml"));
		 databaseTester.setDataSet( dataSet );
		 databaseTester.onSetup();
	}
	
	@After
	public void tearDown() throws Exception{
		databaseTester.onTearDown();
	}
	
	/*@Test
	public void iniciarPedido(){
		long idPedido = pedidoDomainService.iniciarPedido(CONSULTORA_MARILENE_ID);
		Pedido pedido = pedidoRepository.findById(idPedido);
		assertNotNull(pedido);
		Consultora consultora = (Consultora)getField(pedido, "consultora");
		assertNotNull(consultora);
		assertEquals("MARILENE", consultora.nome());
	}*/
	
	/*@Test(expected=IllegalStateException.class)
	public void iniciarPedidoSemConsultora(){
		pedidoDomainService.iniciarPedido(NULL_CONSULTORA_ID);
	}*/
	

	@Test
	public void adicionarProduto() {
		IPedido pedido = pedidoRepository.findById(PEDIDO_SIMPLES);
		IProduto produto = produtoRepository.findById(PRODUTO_CREME_ID);
		
		pedido.adiciconarProdutos(produto, 1);
		
		Collection<Item> itens = (Collection<Item>) getField(pedido, "itens");

		assertEquals(1, itens.size());
		assertEquals(Double.valueOf(10.0), getField(pedido,"total"));
		assertEquals( 10, getField(pedido,"pontuacao"));
	}
	
	@Test
	public void reservarEstoqueAoAdicionarProduto() {
		IConsultora consultora= new Consultora.Builder().pontMaxima(1000).build();
		IPedido pedido = new Pedido.Builder().consultora(consultora).build();
		
		IProduto produto = new Produto.Builder()
				.estoque(new Estoque(100,0))
				.preco(10.0)
				.pontos(10).build();
		
		pedido.adiciconarProdutos(produto, 1);
		
		Estoque estoque = (Estoque) getField(produto, "estoque");
		int qtdReservado =  (Integer) getField(estoque,"reservado");
		assertEquals(99,estoque.disponivel());
		assertEquals(1,qtdReservado);
		
	}
	
	@Test(expected = EstoqueIndisponivelException.class)
	public void adicionarProdutoSemEstoque() {
		IConsultora consultora= new Consultora.Builder().pontMaxima(1000).build();
		IPedido pedido = new Pedido.Builder().consultora(consultora).build();
		
		IProduto produto = new Produto.Builder()
				.estoque(new Estoque(0,0))
				.preco(10.0)
				.pontos(10).build();
		
		pedido.adiciconarProdutos(produto, 1);
	}

	@Test(expected = PontuacaoMaximaExcedidaException.class)
	public void adicionarProdutoPontuacaoExcedida() {
		IConsultora consultora= new Consultora.Builder().pontMaxima(100).build();
		IPedido pedido = new Pedido.Builder().consultora(consultora).build();
		
		IProduto produto = new Produto.Builder().estoque(new Estoque(100,0)).preco(10.0)
				.pontos(10).build();
		pedido.adiciconarProdutos(produto, 20);
	}
	
	@Test
	public void finalizarPedido() {
		IPedido pedido = pedidoRepository.findById(PEDIDO_SIMPLES);
		IProduto produto = produtoRepository.findById(PRODUTO_CREME_ID);
		IProduto produtoSpy = Mockito.spy(produto);
		
		pedido.adiciconarProdutos(produtoSpy, 1);
		
		assertEquals(IPedido.SituacaoPedido.INICIADO, getField(pedido, "situacao"));
		
		pedido.finalizar();

		assertEquals(IPedido.SituacaoPedido.FINALIZADO, getField(pedido, "situacao"));
		Mockito.verify(produtoSpy).reduzirEstoqueEm(1);
	}

}
