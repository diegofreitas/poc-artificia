package br.com.artificia.domain.model.pedido;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.artificia.domain.model.estoque.IProduto;
import br.com.artificia.domain.model.estoque.Produto;


public class ItemTest {

	@Test
	public void deveCalcularValorTotalItemPontuacaoAoCriarItem(){
		IProduto produto = new Produto.Builder().preco(10.0).pontos(10).build();
		Item item = new Item(produto,1);
		assertEquals(10,item.pontuacao());
		assertEquals( Double.valueOf(10.0),Double.valueOf(item.total()));
	}

}
