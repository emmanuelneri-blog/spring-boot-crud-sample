package br.com.devcia.demo.repository;

import br.com.devcia.demo.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, QuerydslPredicateExecutor<Pessoa> {

    Optional<Pessoa> buscarPorCpf(final @Param("cpf") String cpf);

}
