package com.oak.beardbuddy.domain.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoCadastroDTO(
        @NotBlank
        String nome,
        @NotNull
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        Double preco,
        @NotNull
        EnumTipo tipo,
        Integer valorEmPontos) {
}
