package com.oak.beardbuddy.domain.fatura;

import jakarta.validation.constraints.NotNull;

public record FaturaCadastroDTO(
        String cpfCliente,
        @NotNull
        Long id_venda,
        @NotNull
        EnumTipo tipo,
        @NotNull
        Boolean pagoEmPontos) {
}
