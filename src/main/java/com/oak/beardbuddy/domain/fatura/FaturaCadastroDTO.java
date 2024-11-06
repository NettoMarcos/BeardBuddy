package com.oak.beardbuddy.domain.fatura;

import jakarta.validation.constraints.NotNull;

public record FaturaCadastroDTO(
        String cpfCliente,
        @NotNull
        Long idProdOrServ,
        @NotNull
        EnumTipo tipo) {
}
