package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import br.com.artificia.domain.model.estoque.Produto;


public class ItemTest {

	
	@Test
	public void itemComUmProduto(){
		Produto produto = new Produto.Builder().preco(10.0).pontos(10).build();
		Item item = new Item(produto,BigInteger.ONE);
		assertEquals(BigInteger.TEN,item.pontuacao());
		assertEquals(BigDecimal.valueOf(10.0),item.total());
	}
	
	@Test
	public void itemComDezProdutos(){
		Produto produto = new Produto.Builder().preco(10.0).pontos(10).build();
		Item item = new Item(produto,BigInteger.TEN);
		assertEquals(BigInteger.valueOf(100),item.pontuacao());
		assertEquals(BigDecimal.valueOf(100.0),item.total());
	}

}
