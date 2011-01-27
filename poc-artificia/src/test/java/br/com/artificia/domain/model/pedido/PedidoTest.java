package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;

import javax.annotation.Resource;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.hibernate.cache.ReadWriteCache.Item;
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
import br.com.artificia.domain.model.estoque.Estoque;
import br.com.artificia.domain.model.estoque.EstoqueIndisponivelException;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;
import br.com.artificia.domain.model.pedido.Pedido.SituacaoPedido;
import br.com.artificia.domain.service.IPedidoDomainService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
public class PedidoTest {

	private static final int PEDIDO_SIMPLES = 1000;
	private static final long CONSULTORA_MARILENE_ID = 1;
	private static final long NULL_CONSULTORA_ID = -999999;
	private static final long PRODUTO_CREME_ID = 1;

	@Autowired
	private IProdutoRepository produtoRepository;
	
	@Autowired
	private IPedidoDomainService pedidoDomainService;

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	@Resource(name="dbtester")
	private IDatabaseTester databaseTester;
	private IPedidoRepository pedidoRepositoryMock;
	private IProdutoRepository produtoRepositoryMock;

	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass()
				.getResourceAsStream("/dbunit/PedidoRepository.xml"));
		 databaseTester.setDataSet( dataSet );
		 databaseTester.onSetup();
		 
		 produtoRepositoryMock = Mockito.mock(IProdutoRepository.class);
		 pedidoRepositoryMock = Mockito.mock(IPedidoRepository.class);
	}
	
	@After
	public void tearDown() throws Exception{
		databaseTester.onTearDown();
	}
	
	@Test
	public void iniciarPedido(){
		long idPedido = pedidoDomainService.iniciarPedido(CONSULTORA_MARILENE_ID);
		Pedido pedido = pedidoRepository.findById(idPedido);
		assertNotNull(pedido);
		Consultora consultora = (Consultora)getField(pedido, "consultora");
		assertNotNull(consultora);
		assertEquals("MARILENE", consultora.nome());
	}
	
	@Test(expected=IllegalStateException.class)
	public void iniciarPedidoSemConsultora(){
		pedidoDomainService.iniciarPedido(NULL_CONSULTORA_ID);
	}
	

	@Test
	public void adicionarProduto() {
		Pedido pedido = pedidoRepository.findById(PEDIDO_SIMPLES);
		Produto produto = produtoRepository.findById(PRODUTO_CREME_ID);
		
		setField(pedido, "pedidoRepository", pedidoRepository);
		
		pedido.adiciconarProdutos(produto, BigInteger.ONE);
		
		Collection<Item> itens = (Collection<Item>) getField(pedido, "itens");

		assertEquals(1, itens.size());
		assertEquals(BigDecimal.valueOf(10.0), pedido.total());
		assertEquals(BigInteger.valueOf(10), pedido.pontuacao());
	}
	
	@Test
	public void reservarEstoqueAoAdicionarProduto() {
		Consultora consultora= new Consultora.Builder().pontMaxima(1000).build();
		Pedido pedido = new Pedido.Builder().consultora(consultora).build();
		
		
		setField(pedido, "pedidoRepository", pedidoRepositoryMock);
		Produto produto = new Produto.Builder()
				.estoque(new Estoque(100,0))
				.preco(10.0)
				.pontos(10).build();
		
		
		setField(produto, "produtoRepository", produtoRepositoryMock);
		
		
		pedido.adiciconarProdutos(produto, BigInteger.ONE);
		
		Estoque estoque = (Estoque) getField(produto, "estoque");
		BigInteger qtdReservado = (BigInteger)  getField(estoque,"reservado");
		assertEquals(BigInteger.valueOf(99),estoque.disponivel());
		assertEquals(BigInteger.valueOf(1),qtdReservado);
		
	}
	

	public void reservarEstoqueNovoItem() {
		Pedido pedido = pedidoRepository.findById(PEDIDO_SIMPLES);
		Produto produto = produtoRepository.findById(PRODUTO_CREME_ID);
		pedido.adiciconarProdutos(produto, BigInteger.ONE);
	}

	@Test(expected = EstoqueIndisponivelException.class)
	public void adicionarProdutoSemEstoque() {
		Consultora consultora= new Consultora.Builder().pontMaxima(1000).build();
		Pedido pedido = new Pedido.Builder().consultora(consultora).build();
		setField(pedido, "pedidoRepository", pedidoRepositoryMock);
		
		Produto produto = new Produto.Builder()
				.estoque(new Estoque(0,0))
				.preco(10.0)
				.pontos(10).build();
		pedido.adiciconarProdutos(produto, BigInteger.ONE);
	}

	/**
	 * consultora pontuacao maxima 100
	 * pedido com 200 (20 * 10)
	 */
	@Test(expected = PontuacaoMaximaExcedidaException.class)
	public void adicionarProdutoPontuacaoExcedida() {
		Consultora consultora= new Consultora.Builder().pontMaxima(100).build();
		Pedido pedido = new Pedido.Builder().consultora(consultora).build();

		
		Produto produto = new Produto.Builder().estoque(new Estoque(100,0)).preco(10.0)
				.pontos(10).build();
		pedido.adiciconarProdutos(produto, BigInteger.valueOf(20));
	}
	
	@Test
	public void finalizarPedido() {
		Pedido pedido = pedidoRepository.findById(PEDIDO_SIMPLES);
		Produto produto = produtoRepository.findById(PRODUTO_CREME_ID);
		Produto produtoSpy = Mockito.spy(produto);
		setField(pedido, "pedidoRepository", pedidoRepository);
		
		pedido.adiciconarProdutos(produtoSpy, BigInteger.ONE);
		
		
		assertEquals(SituacaoPedido.INICIADO, getField(pedido, "situacao"));
		//assertEquals(BigInteger.valueOf(1000), getField(getField(produto, "estoque"),"quantidade"));
		//assertEquals(BigInteger.valueOf(1), getField(getField(produto, "estoque"),"reservado"));
		
		pedido.finalizar();

		assertEquals(SituacaoPedido.FINALIZADO, getField(pedido, "situacao"));
		Mockito.verify(produtoSpy).reduzirEstoqueEm(BigInteger.ONE);
		//assertEquals(BigInteger.valueOf(0), getField(getField(produto, "estoque"),"reservado"));
		//assertEquals(BigInteger.valueOf(999), getField(getField(produto, "estoque"),"quantidade"));
		
	}

}
