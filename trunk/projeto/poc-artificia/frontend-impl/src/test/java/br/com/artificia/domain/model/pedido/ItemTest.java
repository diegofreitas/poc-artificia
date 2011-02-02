package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.artificia.domain.model.estoque.Produto;


public class ItemTest {

	@Test
	public void deveCalcularValorTotalItemPontuacaoAoCriarItem(){
		Produto produto =  Produto.fabricaProduto(10.0, 10, "Teste", 1000,0);
		Item item = new Item(produto,1);
		assertEquals(10,item.pontuacao());
		assertEquals( Double.valueOf(10.0),Double.valueOf(item.total()));
	}

}
