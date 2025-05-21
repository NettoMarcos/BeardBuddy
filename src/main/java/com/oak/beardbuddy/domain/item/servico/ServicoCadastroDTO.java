package com.oak.beardbuddy.domain.item.servico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoCadastroDTO(
        @NotBlank
        String nome,
        @NotNull
        BigDecimal preco,
        Integer valorEmPontos
) {
}
