package com.oak.beardbuddy.domain.fatura;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record FaturaDetalhesDTO(Long id, String cpfCliente, Long idProdOrServ, EnumTipo tipo, Date dataPagamento, Double valorFatura) {
    public FaturaDetalhesDTO(Fatura fatura) {
        this(fatura.getId(), fatura.getCpfCliente(), fatura.getIdProdOrServ(), fatura.getTipo(), fatura.getDataPagamento(), fatura.getValorFatura());
    }
}
