package com.oak.beardbuddy.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CLIENTES")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private Integer pontos;

    public Cliente(CLienteCadastrarDTO dto){
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        this. dataNascimento = dto.dataNascimento();
    }

    public void atualizarCliente(ClienteAtualizarDTO dto){
        if (dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.cpf() != null){
            this.cpf = dto.cpf();
        }
        if (dto.telefone() != null){
            this.telefone = dto.telefone();
        }
        if (dto.dataNascimento() != null){
            this.dataNascimento = dto.dataNascimento();
        }
    }

}
