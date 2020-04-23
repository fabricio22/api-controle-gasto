package br.com.controle.gastos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.controle.gastos.models.Categoria;
import br.com.controle.gastos.repository.CategoriaRepository;
import br.com.controle.gastos.vo.CategoriaDescricaoVo;
import br.com.controle.gastos.vo.CategoriaVo;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<CategoriaVo> retornaCategoria(@PathVariable("id") Long id) {
		return this.categoriaRepository.findById(id).map(categoria -> new CategoriaVo(categoria));
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoriaVo criarCategoria(@RequestBody CategoriaDescricaoVo categoriaDescricao) {
		return new CategoriaVo(this.categoriaRepository
				.save(CategoriaDescricaoVo.converterCategoria(categoriaDescricao.getDescricao())));
	}

	@GetMapping("/lista-categoria")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public List<CategoriaVo> listaCategorias() {
		return this.categoriaRepository.findAll().stream().map(categoria -> new CategoriaVo(categoria))
				.collect(Collectors.toList());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCategoria(@PathVariable("id") Long id) {
		this.categoriaRepository.deleteById(id);
	}

	@PatchMapping("/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public CategoriaVo atualizaCategoria(@PathVariable("id") Long id, @RequestBody CategoriaDescricaoVo descricao) {
		return this.categoriaRepository.findById(id).map(categoria -> {
			categoria.setDescricao(descricao.getDescricao());

			this.categoriaRepository.save(categoria);

			return new CategoriaVo(categoria);
		}).get();
	}

}
