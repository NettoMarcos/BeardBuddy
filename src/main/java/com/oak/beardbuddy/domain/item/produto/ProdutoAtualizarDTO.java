package com.oak.beardbuddy.domain.item.produto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoAtualizarDTO(
        @NotNull
        Long id,
        String nome,
        BigDecimal preco,
        Integer valorEmPontos,
        Integer estoque,
        BigDecimal valorComprado
) {
}
