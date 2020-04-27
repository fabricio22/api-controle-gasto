package br.com.controle.gastos.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.controle.gastos.enums.TipoMovimento;
import br.com.controle.gastos.models.Movimentacao;
import br.com.controle.gastos.models.SumarizacaoDia;
import br.com.controle.gastos.repository.MovimentacoesRepository;
import br.com.controle.gastos.repository.SumarizacaoDiaRepository;
import br.com.controle.gastos.vo.MovimentacaoVo;
import br.com.controle.gastos.vo.MovimentoAtualizacaoVo;

@Service
public class MovimentacoesServiceImpl implements MovimentacoesService {

	@Autowired
	MovimentacoesRepository movimentacoesRepository;

	@Autowired
	SumarizacaoDiaRepository sumarizacaoDiaRepository;

	@Override
	public MovimentacaoVo criarMovimento(Movimentacao movimentacao) {

		if (movimentacao.getTipoMovimento() == TipoMovimento.SAIDA) {
			movimentacao.setValorMovimento(BigDecimal.valueOf(movimentacao.getValorMovimento().intValue() * -1));
		}

		if (movimentacao.getTipoMovimento() == TipoMovimento.ENTRADA) {
			movimentacao
					.setValorMovimento(BigDecimal.valueOf(Math.abs(movimentacao.getValorMovimento().doubleValue())));
		}

		this.movimentacoesRepository.save(movimentacao);
		atualizaSumarizacaoDia();

		return new MovimentacaoVo(movimentacao);

	}

	private void atualizaSumarizacaoDia() {
		this.sumarizacaoDiaRepository.deleteAll();
		this.sumarizacaoDiaRepository.getSumarizacaoEntradaDia().forEach(sumarizacao -> {
			this.sumarizacaoDiaRepository.save(new SumarizacaoDia(sumarizacao));
		});
	}

	@Override
	public List<MovimentacaoVo> listaMovimentos() {
		return this.movimentacoesRepository.findAll().stream().map(movimentacao -> new MovimentacaoVo(movimentacao))
				.collect(Collectors.toList());
	}

	@Override
	public void removeMovimento(Long id) {
		this.movimentacoesRepository.deleteById(id);
		atualizaSumarizacaoDia();
	}

	@Override
	public MovimentacaoVo atualizaMovimento(Long id, MovimentoAtualizacaoVo movimentos) {
		return this.movimentacoesRepository.findById(id).map(movimento -> {
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

				this.movimentacoesRepository.save(movimento);
				atualizaSumarizacaoDia();

			} else {
				movimento.setValorMovimento(movimentos.getValorMovimento());
				this.movimentacoesRepository.save(movimento);
			}

			return new MovimentacaoVo(movimento);

		}).get();

	}

}
