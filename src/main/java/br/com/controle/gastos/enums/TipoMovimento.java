package br.com.controle.gastos.enums;

import org.hibernate.validator.internal.util.privilegedactions.GetInstancesFromServiceLoader;

public enum TipoMovimento {
	ENTRADA('E'), SAIDA('S');

	private char tipoMovimento;

	private TipoMovimento(char tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public char getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(char tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

}
