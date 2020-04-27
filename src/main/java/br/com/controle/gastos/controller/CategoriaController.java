package br.com.controle.gastos.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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

import br.com.controle.gastos.services.CategoriaService;
import br.com.controle.gastos.vo.CategoriaDescricaoVo;
import br.com.controle.gastos.vo.CategoriaVo;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping("/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> retornaCategoria(@PathVariable("id") Long id) {
		CategoriaVo categoriaRetornada = categoriaService.retornaCategoria(id);
		return new ResponseEntity<CategoriaVo>(categoriaRetornada, HttpStatus.OK);
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> criarCategoria(@Valid @RequestBody CategoriaDescricaoVo categoriaDescricao,
			Errors errors) {
		if (!errors.hasErrors()) {
			return new ResponseEntity<CategoriaVo>(categoriaService.criarCategoria(categoriaDescricao),
					HttpStatus.CREATED);
		}

		return ResponseEntity.badRequest().body(errors.getAllErrors().stream()
				.map(mensagem -> mensagem.getDefaultMessage()).collect(Collectors.joining(",")));
	}

	@GetMapping("/lista-categoria")
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<?> listaCategorias() {
		return new ResponseEntity<List>(categoriaService.listaCategorias(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteCategoria(@PathVariable("id") Long id) {
		categoriaService.deleteCategoria(id);
	}

	@PatchMapping("/{id}")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<?> atualizaCategoria(@Valid @PathVariable("id") Long id,
			@RequestBody CategoriaDescricaoVo descricao, Errors errors) {

		if (!errors.hasErrors()) {
			return new ResponseEntity<CategoriaVo>(categoriaService.atualizaCategoria(id, descricao), HttpStatus.OK);
		}

		return ResponseEntity.badRequest().body(errors.getAllErrors().stream()
				.map(mensagem -> mensagem.getDefaultMessage()).collect(Collectors.joining(",")));
	}

}
