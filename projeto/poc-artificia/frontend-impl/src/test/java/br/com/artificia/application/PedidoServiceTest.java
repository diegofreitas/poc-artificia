package br.com.artificia.application;

import javax.annotation.Resource;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.estoque.IProdutoRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
public class PedidoServiceTest {


	@Autowired
	private IProdutoRepository produtoRepository;


	
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
		/*Pedido pedido = pedidoRepository.findById(PEDIDO_SIMPLES);
		Produto produto = produtoRepository.findById(PRODUTO_CREME_ID);
		
		pedido.adiciconarProdutos(produto, 1);
		
		Collection<Item> itens = (Collection<Item>) getField(pedido, "itens");

		assertEquals(1, itens.size());
		assertEquals(Double.valueOf(10.0), Double.valueOf(pedido.total()));
		assertEquals( 10, pedido.pontuacao());*/
	}
	
	

}
