package br.com.controle.gastos.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.controle.gastos.models.Categoria;

public class CategoriaVo {

	private Long id;
	@NotBlank(message = "N�o � permitido categoria em branco")
	@NotEmpty(message = "N�o � permitido categoria vazia.")
	private String descricao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataInclusao;

	public CategoriaVo() {

	}

	public CategoriaVo(Categoria categoria) {
		this.id = categoria.getId();
		this.descricao = categoria.getDescricao();
		this.dataInclusao = categoria.getDataInclusao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}
