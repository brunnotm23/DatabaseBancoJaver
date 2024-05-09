package io.github.brunnotoscano.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//|******************************************|
//| Descrição: Classe Cliente do banco Javer |
//|******************************************|

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Column(name = "nome")
    @NotEmpty(message = "Campo nome não pode ser vazio.")
    private String nome;

    @Column(name = "telefone")
    @NotNull(message = "Campo telefone não pode ser vazio.")
    private Long telefone;

    @Column(name = "correntista")
    @NotNull(message = "Campo correntista não pode ser vazio.")
    private Boolean correntista;

    @Column(name = "score")
    private float score_credito;

    @Column(name = "saldo")
    private float saldo_cc;


}
