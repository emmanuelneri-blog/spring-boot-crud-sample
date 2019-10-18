package br.com.devcia.demo.repository;

import br.com.devcia.demo.model.Pessoa;
import br.com.devcia.demo.model.QPessoa;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PessoaRepositoryImpl implements PessoaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Pessoa> buscarTodosCustomizado() {
        final JPQLQuery<Pessoa> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery.orderBy(QPessoa.pessoa.nome.asc());
        return jpaQuery.fetch();
    }
}
