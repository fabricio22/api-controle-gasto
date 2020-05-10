package br.com.controle.gastos.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.controle.gastos.models.Movimentacao;
import br.com.controle.gastos.services.MovimentacoesService;
import br.com.controle.gastos.vo.MovimentacaoVo;
import br.com.controle.gastos.vo.MovimentoAtualizacaoVo;
import br.com.controle.gastos.vo.SumarizacaoGeralVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api Rest - Controle de Gastos - Módulo Movimentações")
@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

	@Autowired
	MovimentacoesService movimentacoesServices;

	@ApiOperation(value = "Cria um movimento")
	@PostMapping("criar")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> criarMovimento(@RequestBody Movimentacao movimentacao, Errors errors) {
		if (!errors.hasErrors()) {
			return new ResponseEntity<MovimentacaoVo>(movimentacoesServices.criarMovimento(movimentacao),
					HttpStatus.CREATED);
		}

		return ResponseEntity.badRequest().body(errors.getAllErrors().stream()
				.map(mensagem -> mensagem.getDefaultMessage()).collect(Collectors.joining(",")));

	}

	@ApiOperation(value = "Retorna uma lista de movimento")
	@GetMapping("/lista-movimentos")
	public ResponseEntity<?> buscaMovimento() {
		return new ResponseEntity<List>(movimentacoesServices.listaMovimentos(), HttpStatus.OK);
	}

	@ApiOperation(value = "Atualiza movimento")
	@PatchMapping("/atualizar/{id}")
	public MovimentacaoVo atualizaMovimento(@PathVariable("id") Long id,
			@RequestBody MovimentoAtualizacaoVo movimentos) {
		return movimentacoesServices.atualizaMovimento(id, movimentos);
	}

	@ApiOperation(value = "Remove movimento")
	@DeleteMapping("/remove/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeMovimento(@PathVariable("id") Long id) {
		movimentacoesServices.removeMovimento(id);
	}

	@ApiOperation(value = "Retorna o valor Total Geral")
	@GetMapping("/total-geral")

	public ResponseEntity<?> getTotalGeral() {
		return new ResponseEntity<SumarizacaoGeralVo>(movimentacoesServices.getTotalGeral(), HttpStatus.OK);

	}

}
