package com.oak.beardbuddy.domain.servico;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServicoCadastroDTO(
        @NotBlank
        String nome,
        @NotNull
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        Double preco,
        Integer valorEmPontos) {
}
