package io.github.brunnotoscano.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    @NotEmpty(message = "Campo Nome não pode ser vazio")
    private String nome;

    @Column(name = "telefone")
    @NotNull(message = "Campo telefone não pode ser vazio")
    private Long telefone;

    @Column(name = "correntista")
    @NotNull(message = "Campo correntista não pode ser nulo")
    private Boolean correntista;

    @Column(name = "score")
    private float score_credito;

    @Column(name = "saldo")
    private float saldo_cc;


}
