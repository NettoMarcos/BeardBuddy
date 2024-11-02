package com.oak.beardbuddy.domain.cliente;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "TB_CLIENTES")
@Entity(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private Date dataNascimento;
    private Integer pontos;

    public Cliente(ClienteCadastroDTO dto) {
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        this.dataNascimento = dto.dataNascimento();
    }

    public void atualizarCliente(ClienteAtualizarDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.cpf() != null){
            this.cpf = dto.cpf();
        }
        if(dto.telefone() != null){
            this.telefone = dto.telefone();
        }
        if (dto.dataNascimento() != null){
            this.dataNascimento = dto.dataNascimento();
        }
    }
}
