package com.oak.beardbuddy.domain.cliente;

import java.util.Date;

public record ClienteListarDTO(Long id, String nome, String cpf, String telefone, Date dataNascimento, Integer pontos) {

    public ClienteListarDTO(Cliente cliente){
        this(cliente.getId(),cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento(), cliente.getPontos());
    }

}
