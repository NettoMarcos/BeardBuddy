package com.oak.beardbuddy.domain.produto;

import com.oak.beardbuddy.domain.cliente.ClienteCadastroDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "TB_PRODUTOS")
@Entity(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;
    private Double preco;
    private Integer valorEmPontos;


    public Produto(ProdutoCadastroDTO dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.tipo = dto.tipo();
        this.valorEmPontos = dto.valorEmPontos();
    }
}
