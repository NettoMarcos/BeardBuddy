package com.oak.beardbuddy.domain.item.servico;

import java.math.BigDecimal;

public record ServicoDetalhesDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer valorEmPontos
) {

    public ServicoDetalhesDTO(Servico servico){
        this(servico.getId(), servico.getNome(), servico.getPreco(), servico.getValorEmPontos());
    }
}
