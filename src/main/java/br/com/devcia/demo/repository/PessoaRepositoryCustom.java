package br.com.devcia.demo.repository;

import br.com.devcia.demo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepositoryCustom {

    List<Pessoa> buscarTodosCustomizado();

}
