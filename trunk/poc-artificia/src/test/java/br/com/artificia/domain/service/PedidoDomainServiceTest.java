package br.com.artificia.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
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
import org.springframework.test.util.ReflectionTestUtils;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.estoque.IProdutoRepository;
import br.com.artificia.domain.model.pedido.IPedidoRepository;
import br.com.artificia.domain.model.pedido.Pedido;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PedidoDomainServiceTest {

	private static final int PEDIDO_SIMPLES = 1000;
	private static final long CONSULTORA_MARILENE_ID = 1;
	private static final long NULL_CONSULTORA_ID = -999999;
	private static final long PRODUTO_CREME_ID = 1;

	@Autowired
	private IProdutoRepository produtoRepository;
	
	@Autowired
	private IPedidoDomainService pedidoDomainService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private IPedidoRepository pedidoRepository;
	private IDatabaseTester databaseTester;

	@Before
	public void setUp() throws Exception {
		databaseTester = new DataSourceDatabaseTester(dataSource);
		IDataSet dataSet = new FlatXmlDataSet(this.getClass()
				.getResourceAsStream("/dbunit/PedidoServiceRepository.xml"));
		 databaseTester.setDataSet( dataSet );
		 databaseTester.onSetup();
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
		Consultora consultora = (Consultora) ReflectionTestUtils.getField(pedido, "consultora");
		assertNotNull(consultora);
		assertEquals("MARILENE", consultora.nome());
	}
	
	@Test(expected=IllegalStateException.class)
	public void iniciarPedidoSemConsultora(){
		pedidoDomainService.iniciarPedido(NULL_CONSULTORA_ID);
	}
}
