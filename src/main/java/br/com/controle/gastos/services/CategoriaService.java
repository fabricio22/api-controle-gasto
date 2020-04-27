package br.com.controle.gastos.services;

import java.util.List;

import br.com.controle.gastos.vo.CategoriaDescricaoVo;
import br.com.controle.gastos.vo.CategoriaVo;

public interface CategoriaService {
	
	public CategoriaVo retornaCategoria(Long id);
	
	public CategoriaVo criarCategoria(CategoriaDescricaoVo categoriaDesricaoVo);
	
	public List<CategoriaVo> listaCategorias();
	
	public void deleteCategoria(Long id);
	
	public CategoriaVo atualizaCategoria(Long id, CategoriaDescricaoVo descricao);

}
