package br.com.controle.gastos.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SumarizacaoGeralVo {

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	private BigDecimal valorTotal;

	public SumarizacaoGeralVo() {

	}

	public SumarizacaoGeralVo(BigDecimal valorTotal) {
		this.data = LocalDate.now();
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
}
