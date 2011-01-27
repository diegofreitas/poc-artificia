package br.com.artificia.domain.model.estoque;

import java.math.BigInteger;

import javax.persistence.Embeddable;

@Embeddable
public class Estoque {

	private BigInteger quantidade = BigInteger.ZERO;
	
	private BigInteger reservado = BigInteger.ZERO;
	
	
	
	public Estoque(int quantidade, int reservado) {
		this.quantidade = BigInteger.valueOf(quantidade);
		this.reservado = BigInteger.valueOf(reservado);
	}

	Estoque() {}

	public boolean isEstoqueDisponivel(BigInteger requerida) {
		return requerida.intValue() <= disponivel().intValue();
	}

	public void reservarEm(BigInteger aReservar) {
		if(isEstoqueDisponivel(aReservar)){
			this.reservado = this.reservado.add(aReservar);
		}else{
			throw new EstoqueIndisponivelException();
		}
	}

	public BigInteger disponivel() {
		BigInteger disponivel = quantidade.subtract(reservado);
		return disponivel.intValue() < 0 ? BigInteger.ZERO : disponivel;
	}

	public void reduzirEm(BigInteger one) {
		this.quantidade = this.quantidade.subtract(one);
		this.reservado = this.reservado.subtract(one);
	}
}
