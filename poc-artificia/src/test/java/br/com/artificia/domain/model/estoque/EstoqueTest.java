package br.com.artificia.domain.model.estoque;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class EstoqueTest {

	@Test
	public void testIsEstoqueDisponivelSemReserva() {
		Estoque estoque = new Estoque(100,0);
		assertTrue(estoque.isEstoqueDisponivel(BigInteger.TEN));
	}
	
	@Test
	public void testIsEstoqueDisponivelComReserva() {
		Estoque estoque = new Estoque(100,90);
		assertTrue(estoque.isEstoqueDisponivel(BigInteger.TEN));
	}

	@Test
	public void testReservarEstoqueEm() {
		Estoque estoque = new Estoque(100,0);
		estoque.reservarEm(BigInteger.TEN);
		assertEquals(BigInteger.TEN,ReflectionTestUtils.getField(estoque,"reservado"));
	}
	
	@Test(expected=EstoqueIndisponivelException.class)
	public void testReservarSemDisponibilidade() {
		Estoque estoque = new Estoque(100,100);
		estoque.reservarEm(BigInteger.TEN);
	}

	@Test
	public void testDisponivelSemReservaComEstoque() {
		Estoque estoque = new Estoque(100,0);
		assertEquals(BigInteger.valueOf(100),estoque.disponivel());
	}
	
	@Test
	public void testDisponivelComReservaComEstoque() {
		Estoque estoque = new Estoque(100,10);
		assertEquals(BigInteger.valueOf(90),estoque.disponivel());
	}
	
	@Test
	public void testDisponivelComReservaSemEstoque() {
		Estoque estoque = new Estoque(0,10);
		assertEquals(BigInteger.valueOf(0),estoque.disponivel());
	}
	
	

}
