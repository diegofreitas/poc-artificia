package br.com.artificia.infrastructure.persistence;

import static org.junit.Assert.assertEquals;

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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import br.com.artificia.domain.model.consultora.Consultora;
import br.com.artificia.domain.model.consultora.IConsultoraRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
public class ConsultoraRepositoryTest {

	@Resource(name="dbtester")
	private IDatabaseTester databaseTester;

	@Autowired
	private IConsultoraRepository<Consultora> consultoraRepository;

	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass()
				.getResourceAsStream("/dbunit/ConsultoraRepository.xml"));
		 databaseTester.setDataSet( dataSet );
		 databaseTester.onSetup();
	}
	
	@After
	public void tearDown() throws Exception{
		databaseTester.onTearDown();
	}
	
	@Test
	public void deveEncontrarConsultoraPreExistentePorId() {
		 Consultora consultora = this.consultoraRepository.findById(2);
		 assertEquals("Consultora cadastrada no dataset nao encontrada",
				 "CONSULTORA_TESTE",ReflectionTestUtils.getField(consultora,"nome"));
	}

}
