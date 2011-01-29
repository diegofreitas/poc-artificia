package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import br.com.artificia.domain.model.estoque.Produto;


public class ItemTest {

	@Test
	public void deveCalcularValorTotalItemPontuacaoAoCriarItem(){
		Produto produto = new Produto.Builder().preco(10.0).pontos(10).build();
		Item item = new Item(produto,BigInteger.ONE);
		assertEquals(BigInteger.TEN,item.pontuacao());
		assertEquals(BigDecimal.valueOf(10.0),item.total());
	}

}
