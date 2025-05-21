package com.oak.beardbuddy.domain.cliente;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ClienteAtualizarDTO(

        @NotNull
        Long id,
        String nome,
        String cpf,
        String telefone,
        LocalDate dataNascimento
) {
}
