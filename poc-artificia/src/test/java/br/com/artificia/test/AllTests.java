package br.com.artificia.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.artificia.domain.model.estoque.EstoqueTest;
import br.com.artificia.domain.model.pedido.ItemTest;
import br.com.artificia.domain.model.pedido.PedidoTest;
import br.com.artificia.domain.service.PedidoDomainServiceTest;


@RunWith(Suite.class)
@SuiteClasses({
	PedidoTest.class,
	EstoqueTest.class,
	ItemTest.class,
	PedidoDomainServiceTest.class,
	EstoqueTest.class
})
public class AllTests {
}
