package io.github.brunnotoscano.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    @NotEmpty
    private String nome;

    @Column(name = "telefone")
    @NotNull
    private Long telefone;

    @Column(name = "correntista")
    @NotNull
    private Boolean correntista;

    @Column(name = "score")
    private float score_credito;

    @Column(name = "saldo")
    private float saldo_cc;


}
