package br.com.controle.gastos.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.controle.gastos.models.Categoria;

public class CategoriaDescricaoVo {

	@NotBlank(message = "Não é permitido categoria em branco")
	@NotEmpty(message = "Não é permitido categoria vazia.")
	private String descricao;

	public CategoriaDescricaoVo() {

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Categoria converterCategoria(String descricao) {
		Categoria categoria = new Categoria();
		categoria.setDescricao(descricao);
		categoria.setDataInclusao(new Date());
		
		return categoria;
	}
}
