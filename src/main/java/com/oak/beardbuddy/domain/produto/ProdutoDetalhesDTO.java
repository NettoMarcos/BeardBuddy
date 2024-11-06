package com.oak.beardbuddy.domain.produto;


public record ProdutoDetalhesDTO(Long id, String nome, Double preco,Integer quantidade, Integer valorEmPontos) {
    public ProdutoDetalhesDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getPreco(),produto.getQuantidade(), produto.getValorEmPontos());
    }
}
