package br.com.controle.gastos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.gastos.models.Categoria;
import br.com.controle.gastos.repository.CategoriaRepository;
import br.com.controle.gastos.vo.CategoriaDescricaoVo;
import br.com.controle.gastos.vo.CategoriaVo;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public CategoriaVo retornaCategoria(Long id) {
		return categoriaRepository.findById(id).map(categoria -> new CategoriaVo(categoria)).get();
	}

	@Override
	public CategoriaVo criarCategoria(CategoriaDescricaoVo categoriaDesricaoVo) {
		 Categoria categoria = categoriaRepository.save(CategoriaDescricaoVo.converterCategoria(categoriaDesricaoVo.getDescricao()));
		return new CategoriaVo(categoria);
	}

	@Override
	public List<CategoriaVo> listaCategorias() {
		return categoriaRepository.findAll()
				.stream()
				.map(categoria -> new CategoriaVo(categoria))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteCategoria(Long id) {
		categoriaRepository.deleteById(id);		
	}

	@Override
	public CategoriaVo atualizaCategoria(Long id, CategoriaDescricaoVo descricao) {
			Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);
			
			if(optionalCategoria.isPresent()) { 
				Categoria categoria = optionalCategoria.get();
				categoria.setDescricao(descricao.getDescricao());
				categoriaRepository.save(categoria);
				return new CategoriaVo(categoria);
			}
			
			return null;
	}
	
	

}
