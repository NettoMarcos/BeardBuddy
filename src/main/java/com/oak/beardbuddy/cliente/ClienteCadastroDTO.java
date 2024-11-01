package com.oak.beardbuddy.cliente;

import java.util.Date;

public record ClienteCadastroDTO(String nome, String cpf, String telefone, Date dataNascimento) {
}
