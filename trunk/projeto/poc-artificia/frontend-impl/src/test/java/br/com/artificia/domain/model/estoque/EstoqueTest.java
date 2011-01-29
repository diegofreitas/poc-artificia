package br.com.artificia.domain.model.estoque;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class EstoqueTest {

	@Test
	public void deveEstarDisponivelQuandoSemReservaComQuantidade() {
		Estoque estoque = new Estoque(100,0);
		assertTrue(estoque.isEstoqueDisponivel(10));
	}
	
	@Test
	public void testIsEstoqueDisponivelComReserva() {
		Estoque estoque = new Estoque(100,90);
		assertTrue(estoque.isEstoqueDisponivel(10));
	}

	@Test
	public void testReservarEstoqueEm() {
		Estoque estoque = new Estoque(100,0);
		estoque.reservarEm(10);
		assertEquals(10,ReflectionTestUtils.getField(estoque,"reservado"));
	}
	
	@Test(expected=EstoqueIndisponivelException.class)
	public void testReservarSemDisponibilidade() {
		Estoque estoque = new Estoque(100,100);
		estoque.reservarEm(10);
	}

	@Test
	public void testDisponivelSemReservaComEstoque() {
		Estoque estoque = new Estoque(100,0);
		assertEquals(100,estoque.disponivel());
	}
	
	@Test
	public void testDisponivelComReservaComEstoque() {
		Estoque estoque = new Estoque(100,10);
		assertEquals(90,estoque.disponivel());
	}
	
	@Test
	public void testDisponivelComReservaSemEstoque() {
		Estoque estoque = new Estoque(0,10);
		assertEquals(0,estoque.disponivel());
	}
	
	

}
