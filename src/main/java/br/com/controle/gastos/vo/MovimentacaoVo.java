package br.com.controle.gastos.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.controle.gastos.models.Movimentacao;

public class MovimentacaoVo {

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataReferencia;
	private String descricaoMovimento;
	private BigDecimal valorMovimento;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataInclusao;
	private char tipoMovimento;
	private Long idCategoria;

	public MovimentacaoVo(Movimentacao movimentacao) {
		this.id = movimentacao.getId();
		this.dataReferencia = movimentacao.getDataReferencia();
		this.descricaoMovimento = movimentacao.getDescricaoMovimento();
		this.valorMovimento = movimentacao.getValorMovimento();
		this.tipoMovimento = movimentacao.getTipoMovimento().getTipoMovimento();
		this.idCategoria = movimentacao.getCategoria().getId();
		this.dataInclusao = movimentacao.getDataInclusao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public char getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(char tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	
}
