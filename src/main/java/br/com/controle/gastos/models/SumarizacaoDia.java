package br.com.controle.gastos.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.controle.gastos.vo.SumarizacaoVo;

@Entity
@Table(name = "SUMARIZACAODIA")
public class SumarizacaoDia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SUMARIZACAO_DIA")
	private Long id;
	@Column(name = "DATA")
	private Date data;
	@Column(name = "VALOR_TOTAL")
	private BigDecimal valorTotal;
	@Column(name = "DATA_INCLUSAO")
	private Date dataInclusao;

	public SumarizacaoDia() {

	}

	public SumarizacaoDia(SumarizacaoVo sumarizacaoVo) {
		this.data = sumarizacaoVo.getData();
		this.valorTotal = sumarizacaoVo.getValorTotal();
		this.dataInclusao = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
