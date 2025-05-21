package com.oak.beardbuddy.domain.itemVendido;


import com.oak.beardbuddy.domain.item.Item;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemVendidoCadastroDTO(
        @NotNull
        Item Item,
        @NotNull
        Integer quantidade,
        @NotNull
        BigDecimal valorTotal
) {
}
