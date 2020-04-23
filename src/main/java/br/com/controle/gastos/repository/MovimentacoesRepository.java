package br.com.controle.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controle.gastos.models.Movimentacao;

public interface MovimentacoesRepository extends JpaRepository<Movimentacao, Long> {

}
