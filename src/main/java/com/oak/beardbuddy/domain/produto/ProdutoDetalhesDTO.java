package com.oak.beardbuddy.domain.produto;


public record ProdutoDetalhesDTO(Long id, String nome, Double preco,EnumTipo tipo, Integer valorEmPontos) {
    public ProdutoDetalhesDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(),produto.getTipo(), produto.getValorEmPontos());
    }
}
