package com.oak.beardbuddy.domain.servico;

import jakarta.validation.constraints.NotNull;

public record ServicoAtualizarDTO(
        @NotNull
        Long id,
        String nome,
        Double preco,
        Integer valorEmPontos) {
}
