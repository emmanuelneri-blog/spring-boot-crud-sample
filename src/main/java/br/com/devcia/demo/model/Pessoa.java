package br.com.devcia.demo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "cpf")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cpf"}, name = "pessoa_uk")})
@NamedQueries(
        @NamedQuery(name = "Pessoa.buscarPorCpf", query = "select p from Pessoa p where p.cpf = :cpf")
)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String cpf;

    private String nome;

}
