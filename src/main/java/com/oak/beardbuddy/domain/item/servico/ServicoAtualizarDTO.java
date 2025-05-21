package com.oak.beardbuddy.domain.item.servico;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoAtualizarDTO(
        @NotNull
        Long id,
        String nome,
        BigDecimal preco,
        Integer valorEmPontos
) {
}
