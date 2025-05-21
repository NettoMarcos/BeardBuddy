package com.oak.beardbuddy.domain.cliente;

import java.time.LocalDate;

public record ClienteDetalhesDTO(

        Long id,
        String nome,
        String cpf,
        String telefone,
        LocalDate dataNascimento
){
    public ClienteDetalhesDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento());
    }
}
