package br.com.controle.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.controle.gastos.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
