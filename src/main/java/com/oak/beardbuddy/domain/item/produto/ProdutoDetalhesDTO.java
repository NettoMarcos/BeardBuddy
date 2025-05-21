package com.oak.beardbuddy.domain.item.produto;

import java.math.BigDecimal;

public record ProdutoDetalhesDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer valorEmPontos,
        Integer estoque,
        BigDecimal valorComprado
) {
    public ProdutoDetalhesDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco(), produto.getValorEmPontos(), produto.getEstoque(), produto.getValorComprado());
    }
}
