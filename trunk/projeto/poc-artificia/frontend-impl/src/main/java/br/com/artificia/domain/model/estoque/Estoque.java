package br.com.artificia.domain.model.estoque;

import java.math.BigInteger;

import javax.persistence.Embeddable;

@Embeddable
public class Estoque {

	private int quantidade = 0;
	
	private int reservado = 0;
	
	
	
	public Estoque(int quantidade, int reservado) {
		this.quantidade = quantidade;
		this.reservado = reservado;
	}

	Estoque() {}

	public boolean isEstoqueDisponivel(int requerida) {
		return requerida <= disponivel();
	}

	public void reservarEm(int aReservar) {
		if(isEstoqueDisponivel(aReservar)){
			this.reservado += aReservar;
		}else{
			throw new EstoqueIndisponivelException();
		}
	}

	public int disponivel() {
		int disponivel = quantidade - reservado;
		return disponivel < 0 ? 0 : disponivel;
	}

	public void reduzirEm(int one) {
		this.quantidade = this.quantidade - one ;
		this.reservado = this.reservado - one;
	}
}
