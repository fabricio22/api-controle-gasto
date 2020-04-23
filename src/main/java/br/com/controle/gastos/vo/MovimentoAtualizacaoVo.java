package br.com.controle.gastos.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.controle.gastos.enums.TipoMovimento;
import br.com.controle.gastos.models.Categoria;

public class MovimentoAtualizacaoVo {

	private Date dataReferencia;
	private String descricaoMovimento;
	private BigDecimal valorMovimento;
	private TipoMovimento tipoMovimento;
	private Categoria categoria;

	public MovimentoAtualizacaoVo() {

	}

	public Date getDataReferencia() {
		return dataReferencia;
	}

	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}

	public String getDescricaoMovimento() {
		return descricaoMovimento;
	}

	public void setDescricaoMovimento(String descricaoMovimento) {
		this.descricaoMovimento = descricaoMovimento;
	}

	public BigDecimal getValorMovimento() {
		return valorMovimento;
	}

	public void setValorMovimento(BigDecimal valorMovimento) {
		this.valorMovimento = valorMovimento;
	}

	public TipoMovimento getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(TipoMovimento tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
