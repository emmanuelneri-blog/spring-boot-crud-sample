package br.com.devcia.demo.service;

import br.com.devcia.demo.exception.ParametroInvalidoException;
import br.com.devcia.demo.model.Pessoa;
import br.com.devcia.demo.model.QPessoa;
import br.com.devcia.demo.repository.PessoaRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(final Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa salvar(final Pessoa pessoa) {
        if (pessoa.getId() != null) {
            throw new ParametroInvalidoException("Id não pode estar preenchido na criação");
        }

        return pessoaRepository.save(pessoa);
    }


    public Pessoa atualizar(final Long id, final Pessoa pessoa) {
        if (id == null) {
            throw new ParametroInvalidoException("Id não pode ser nulo");
        }

        if (pessoa.getId() != null && !id.equals(pessoa.getId())) {
            throw new ParametroInvalidoException("Id do parâmetro diferente do id do body da pessoa");
        }

        if (pessoa.getId() == null) {
            pessoa.setId(id);
        }

        return pessoaRepository.save(pessoa);
    }

    public void deletar(final Long id) {
        pessoaRepository.deleteById(id);
    }

    public Optional<Pessoa> buscarPorCpf(String cpf) {
        return pessoaRepository.buscarPorCpf(cpf);
    }

    public Page<Pessoa> buscarTodosPaginado(final int pagina, final int tamanho) {
        final PageRequest pageRequest = PageRequest.of(pagina, tamanho, Sort.by("id"));
        return pessoaRepository.findAll(pageRequest);
    }

    public List<Pessoa> buscarPorNome(final String nome) {
        final BooleanBuilder predicate = new BooleanBuilder();

        if(nome != null && !nome.isEmpty()) {
            predicate.and(QPessoa.pessoa.nome.containsIgnoreCase(nome));
        }

        return StreamSupport.stream(pessoaRepository.findAll(predicate).spliterator(), false)
                .collect(Collectors.toList());
    }
}
