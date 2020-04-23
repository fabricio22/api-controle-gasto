package br.com.controle.gastos.controller;

import java.math.BigDecimal;
import java.util.List;
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

import br.com.controle.gastos.enums.TipoMovimento;
import br.com.controle.gastos.models.Movimentacao;
import br.com.controle.gastos.models.SumarizacaoDia;
import br.com.controle.gastos.repository.MovimentacoesRepository;
import br.com.controle.gastos.repository.SumarizacaoDiaRepository;
import br.com.controle.gastos.vo.MovimentacaoVo;
import br.com.controle.gastos.vo.MovimentoAtualizacaoVo;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

	@Autowired
	MovimentacoesRepository movimentacaoRepository;

	@Autowired
	SumarizacaoDiaRepository sumarizacaoDiaReprository;

	@PostMapping("criar")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public MovimentacaoVo criarMovimento(@RequestBody Movimentacao movimentacao) {

		if (movimentacao.getTipoMovimento() == TipoMovimento.SAIDA) {
			movimentacao.setValorMovimento(BigDecimal.valueOf(movimentacao.getValorMovimento().intValue() * -1));
		}

		if (movimentacao.getTipoMovimento() == TipoMovimento.ENTRADA) {
			movimentacao
					.setValorMovimento(BigDecimal.valueOf(Math.abs(movimentacao.getValorMovimento().doubleValue())));
		}

		this.movimentacaoRepository.save(movimentacao);
		atualizaSumarizacao();

		return new MovimentacaoVo(movimentacao);
	}

	@GetMapping("/lista-movimentos")
	public List<MovimentacaoVo> buscaMovimento() {
		return this.movimentacaoRepository.findAll().stream().map(movimentacao -> new MovimentacaoVo(movimentacao))
				.collect(Collectors.toList());
	}

	@PatchMapping("/atualizar/{id}")
	public MovimentacaoVo atualizaMovimento(@PathVariable("id") Long id,
			@RequestBody MovimentoAtualizacaoVo movimentos) {
		return this.movimentacaoRepository.findById(id).map(movimento -> {
			movimento.setCategoria(movimentos.getCategoria());
			movimento.setDataReferencia(movimentos.getDataReferencia());
			movimento.setDescricaoMovimento(movimentos.getDescricaoMovimento());
			movimento.setTipoMovimento(movimentos.getTipoMovimento());

			if ((TipoMovimento.SAIDA == movimentos.getTipoMovimento()
					&& movimentos.getValorMovimento().doubleValue() > 0)
					|| (TipoMovimento.ENTRADA == movimentos.getTipoMovimento()
							&& movimentos.getValorMovimento().doubleValue() < 0)) {

				throw new RuntimeException("Valor do movimento é inválido para o tipo de movimento.");
			}

			if (movimento.getValorMovimento() != movimentos.getValorMovimento()) {
				movimento.setValorMovimento(movimentos.getValorMovimento());

				this.movimentacaoRepository.save(movimento);
				atualizaSumarizacao();

			} else {
				movimento.setValorMovimento(movimentos.getValorMovimento());
				this.movimentacaoRepository.save(movimento);
			}

			return new MovimentacaoVo(movimento);

		}).get();
	}

	@DeleteMapping("/remove/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void removeMovimento(@PathVariable("id") Long id) {
		this.movimentacaoRepository.deleteById(id);
		atualizaSumarizacao();
	}

	private void atualizaSumarizacao() {
		this.sumarizacaoDiaReprository.deleteAll();
		this.sumarizacaoDiaReprository.getSumarizacaoEntradaDia().forEach(sumarizacao -> {
			this.sumarizacaoDiaReprository.save(new SumarizacaoDia(sumarizacao));
		});
	}

}
