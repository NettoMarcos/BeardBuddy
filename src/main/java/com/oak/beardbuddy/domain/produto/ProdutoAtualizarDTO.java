package com.oak.beardbuddy.domain.produto;

import jakarta.validation.constraints.NotNull;

public record ProdutoAtualizarDTO(
        @NotNull
        Long id,
        String nome,
        Double preco,
        Integer quantidade,
        Integer valorEmPontos) {

}
