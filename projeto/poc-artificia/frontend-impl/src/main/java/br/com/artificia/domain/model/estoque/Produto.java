package br.com.artificia.domain.model.estoque;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Configurable;


@Entity
@Configurable
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private double preco;
	
	@Embedded
	private Estoque estoque;

	private int pontos;

	private String descricao;

	
	Produto(){}

	public double preco() {
		return preco;
	}
	
	public int pontos() {
		return this.pontos;
	}

	public void reservarEstoqueEm(int quantidade) {
		this.estoque.reservarEm(quantidade);
	}

	public void reduzirEstoqueEm(int one) {
		this.estoque.reduzirEm(one);
	}

	public String descricao() {
		return this.descricao;
	}
	
	public static Produto fabricaProduto(double preco, int pontos, String descricao, int estoque, int reservada){
		Produto produto = new Produto();
		produto.preco = preco;
		produto.descricao= descricao;
		produto.estoque = new Estoque(estoque,reservada);
		produto.pontos = pontos;
		return produto;
	}

}
