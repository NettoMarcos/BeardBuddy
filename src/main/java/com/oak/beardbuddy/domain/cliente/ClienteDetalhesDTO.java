package com.oak.beardbuddy.domain.cliente;

import java.util.Date;

public record ClienteDetalhesDTO(Long id, String nome, String cpf, String telefone, Date dataNascimento, Integer pontos) {
    public ClienteDetalhesDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento(), cliente.getPontos());
    }
}
