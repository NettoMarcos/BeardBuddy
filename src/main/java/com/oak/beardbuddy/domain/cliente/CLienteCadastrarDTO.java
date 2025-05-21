package com.oak.beardbuddy.domain.cliente;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CLienteCadastrarDTO(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        String telefone,
        LocalDate dataNascimento
) {
    public CLienteCadastrarDTO(Cliente cliente){
        this(cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getDataNascimento());
    }
}
