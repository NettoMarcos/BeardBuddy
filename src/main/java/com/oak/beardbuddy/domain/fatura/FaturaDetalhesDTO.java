package com.oak.beardbuddy.domain.fatura;

import com.oak.beardbuddy.domain.cliente.Cliente;

import com.oak.beardbuddy.domain.itemVendido.ItemVendidoDetalheDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record FaturaDetalhesDTO(
        Long id,
        LocalDateTime dataGeraCao,
        BigDecimal valorTotal,
        BigDecimal lucroTotal,
        Cliente cliente,
        List<ItemVendidoDetalheDTO> itensVendidos
) {

    public FaturaDetalhesDTO(Fatura fatura){
        this(
                fatura.getId(),
                fatura.getDataGeracao(),
                fatura.getLucroTotal(),
                fatura.getValorTotal(),
                fatura.getCliente(),
                fatura.getItensVendidos().stream().map(ItemVendidoDetalheDTO::new).collect(Collectors.toList())

        );
    }
}
