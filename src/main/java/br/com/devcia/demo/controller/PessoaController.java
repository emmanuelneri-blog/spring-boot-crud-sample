package br.com.devcia.demo.controller;

import br.com.devcia.demo.model.Pessoa;
import br.com.devcia.demo.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoas")
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodos() {
        return pessoaService.buscarTodos();
    }

    @GetMapping(value = "/paginado")
    public Page<Pessoa> listarTodos(final @RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                    final @RequestParam(value = "total", defaultValue = "10") int total) {
        return pessoaService.buscarTodosPaginado(pagina, total);
    }

    @GetMapping(value = "/{id}")
    public Pessoa buscarPorId(@PathVariable("id") final Long id) {
        return pessoaService.buscarPorId(id).orElse(null);
    }

    @GetMapping(value = "/cpf/{cpf}")
    public Pessoa buscarPorCpf(@PathVariable("cpf") final String cpf) {
        return pessoaService.buscarPorCpf(cpf).orElse(null);
    }

    @GetMapping(value = "/nome/{nome}")
    public List<Pessoa> buscarPorNome(@PathVariable(value = "nome", required = false) final String nome) {
        return pessoaService.buscarPorNome(nome);
    }

    @PutMapping(value = "/{id}")
    public Pessoa atualizar(@PathVariable("id") final Long id, @RequestBody final Pessoa pessoa) {
        return pessoaService.atualizar(id, pessoa);
    }

    @PostMapping
    public Pessoa criar(@RequestBody final Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable("id") final Long id) {
        pessoaService.deletar(id);
    }
}
