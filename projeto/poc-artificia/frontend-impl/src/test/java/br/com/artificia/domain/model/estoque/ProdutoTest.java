package br.com.artificia.domain.model.estoque;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import static org.springframework.test.util.ReflectionTestUtils.*;

public class ProdutoTest {

	@Test
	public void deveReduzirEstoqueApartirDoReservado() {
	    //dado
		int quantidadeEmEstoque = 100;
		int quantidadeAReduzir = 10;
		int quantidadeReservada = 10;
		Produto produto = Produto.fabricaProduto(10.0, 10, "Teste", quantidadeEmEstoque,quantidadeReservada);
		
		//quando
		produto.reduzirEstoqueEm(quantidadeAReduzir);
		
		//then
		Estoque estoque = (Estoque) getField(produto, "estoque");
		assertEquals(90,getField(estoque,"quantidade"));
		assertEquals(0,getField(estoque,"reservado"));
	}

}
