package com.oak.beardbuddy.infra.exception;

public class ProdutoForaDeEstoqueException extends RuntimeException {

    public ProdutoForaDeEstoqueException(String mensagem) {
        super(mensagem);
    }
}
