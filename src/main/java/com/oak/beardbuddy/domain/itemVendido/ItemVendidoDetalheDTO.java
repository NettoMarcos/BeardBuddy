package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.item.Item;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemVendidoDetalheDTO(
        Long id,
        Item Item,
        Integer quantidade,
        BigDecimal valorTotal
) {

    ItemVendidoDetalheDTO(ItemVendido itemVendido){
        this(itemVendido.getId(), itemVendido.getItem(), itemVendido.getQuantidade(),itemVendido.getValorTotal());
    }
}
