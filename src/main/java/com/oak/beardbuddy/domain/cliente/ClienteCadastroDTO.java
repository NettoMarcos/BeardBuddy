package com.oak.beardbuddy.domain.cliente;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record ClienteCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String telefone,
        Date dataNascimento) {

}
