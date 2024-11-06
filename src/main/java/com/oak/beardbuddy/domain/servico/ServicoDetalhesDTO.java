package com.oak.beardbuddy.domain.servico;

public record ServicoDetalhesDTO(Long id, String nome, Double preco, Integer valorEmPontos) {

    public ServicoDetalhesDTO(Servico servico) {
        this(servico.getId(),servico.getNome(),servico.getPreco(), servico.getValorEmPontos());
    }
}
