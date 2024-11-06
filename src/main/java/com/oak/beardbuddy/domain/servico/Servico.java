package com.oak.beardbuddy.domain.servico;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_SERVICOS")
@Entity(name = "servico")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Double preco;
    private Integer valorEmPontos;

    public Servico(ServicoCadastroDTO dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.valorEmPontos = dto.valorEmPontos();
    }

    public void atualizarServico(ServicoAtualizarDTO dto) {

        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.preco() != null){
            this.preco = dto.preco();
        }
        if (dto.valorEmPontos() != null){
            this.valorEmPontos = dto.valorEmPontos();
        }
    }
}
