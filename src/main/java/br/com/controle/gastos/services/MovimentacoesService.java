package br.com.controle.gastos.services;

import java.util.List;

import br.com.controle.gastos.models.Movimentacao;
import br.com.controle.gastos.vo.MovimentacaoVo;
import br.com.controle.gastos.vo.MovimentoAtualizacaoVo;
import br.com.controle.gastos.vo.SumarizacaoGeralVo;

public interface MovimentacoesService {
	
	public MovimentacaoVo criarMovimento(Movimentacao movimentacao);
	public List<MovimentacaoVo> listaMovimentos();
	public void removeMovimento(Long id);
	public MovimentacaoVo atualizaMovimento(Long id, MovimentoAtualizacaoVo movimentos);
	public SumarizacaoGeralVo getTotalGeral();

}
