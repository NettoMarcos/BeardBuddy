package com.oak.beardbuddy.domain.item.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCadastroDTO(
        @NotBlank
        String nome,
        @NotNull
        BigDecimal preco,
        Integer valorEmPontos,
        @NotNull
        Integer estoque,
        @NotNull
        BigDecimal valorComprado


) {
}
