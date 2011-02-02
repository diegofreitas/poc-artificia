package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.*;
import static org.springframework.test.util.ReflectionTestUtils.getField;
import static org.mockito.Mockito.*;
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
import br.com.artificia.domain.model.estoque.Estoque;
import br.com.artificia.domain.model.estoque.EstoqueIndisponivelException;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.estoque.Produto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
public class PedidoTest {

	private static final int PEDIDO_SIMPLES = 1000;
	private static final long PRODUTO_CREME_ID = 1;

	@Autowired
	private IProdutoRepository<Produto> produtoRepository;

	@Autowired
	private IPedidoRepository<Pedido> pedidoRepository;
	private Consultora consultora;
	private Produto produto;
	private Pedido pedido;
	
	/*@Resource(name="dbtester")
	private IDatabaseTester databaseTester;*/

	@Before
	public void setUp() throws Exception {
		/*IDataSet dataSet = new FlatXmlDataSet(this.getClass()
				.getResourceAsStream("/dbunit/PedidoRepository.xml"));
		 databaseTester.setDataSet( dataSet );
		 databaseTester.onSetup();*/
		
		consultora = new Consultora(1,100,"MARILENE");
		produto = Produto.fabricaProduto(10.0, 10, "CREME", 100,0);
		pedido = new Pedido.Builder().consultora(consultora).build();
	}
	
	@After
	public void tearDown() throws Exception{
		//databaseTester.onTearDown();
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
		
		
		
		pedido.adiciconarProdutos(produto, 1);
		
		Collection<Item> itens = (Collection<Item>) getField(pedido, "itens");

		assertEquals(1, itens.size());
		assertEquals(Double.valueOf(10.0), getField(pedido,"total"));
		assertEquals( 10, getField(pedido,"pontuacao"));
	}
	
	@Test
	public void reservarEstoqueAoAdicionarProduto() {
		Pedido pedido = new Pedido.Builder().consultora(consultora).build();
		Produto produtoSpy = spy(produto);
		pedido.adiciconarProdutos(produtoSpy, 1);
		verify(produtoSpy).reservarEstoqueEm(1);

	}
	
	/*@Test(expected = EstoqueIndisponivelException.class)
	public void adicionarProdutoSemEstoque() {
		Consultora consultora= new Consultora.Builder().pontMaxima(1000).build();
		Pedido pedido = new Pedido.Builder().consultora(consultora).build();
		
		Produto produto = new Produto.Builder()
				.estoque(new Estoque(0,0))
				.preco(10.0)
				.pontos(10).build();
		
		pedido.adiciconarProdutos(produto, 1);
	}
*/
	
	@Test(expected = PontuacaoMaximaExcedidaException.class)
	public void adicionarProdutoPontuacaoExcedida() {
		Pedido pedido = new Pedido.Builder().consultora(consultora).build();
		pedido.adiciconarProdutos(produto, 20);
	}
	
	@Test
	public void finalizarPedido() {

		Produto produtoSpy = Mockito.spy(produto);
		
		pedido.adiciconarProdutos(produtoSpy, 1);
		
		assertEquals(SituacaoPedido.INICIADO, getField(pedido, "situacao"));
		
		pedido.finalizar();

		assertEquals(SituacaoPedido.FINALIZADO, getField(pedido, "situacao"));
		Mockito.verify(produtoSpy).reduzirEstoqueEm(1);
	}

}
