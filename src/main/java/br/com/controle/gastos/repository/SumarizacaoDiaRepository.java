package br.com.controle.gastos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.controle.gastos.models.SumarizacaoDia;
import br.com.controle.gastos.vo.SumarizacaoGeralVo;
import br.com.controle.gastos.vo.SumarizacaoVo;

@Repository
public interface SumarizacaoDiaRepository extends JpaRepository<SumarizacaoDia, Long> {

	@Query(value = "SELECT new br.com.controle.gastos.vo.SumarizacaoVo(m.dataReferencia AS data,"
			+ " SUM(m.valorMovimento) AS valorTotal)" + " FROM Movimentacao m" + " GROUP BY m.dataReferencia")
	public List<SumarizacaoVo> getSumarizacaoEntradaDia();

	@Query(value = "SELECT new br.com.controle.gastos.vo.SumarizacaoGeralVo(SUM(m.valorMovimento) AS valorTotal) FROM Movimentacao m")
	public SumarizacaoGeralVo getTotal();

}
