package br.com.controle.gastos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.controle.gastos.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
