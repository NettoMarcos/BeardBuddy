package com.oak.beardbuddy.domain.item.produto;

import com.oak.beardbuddy.domain.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;


@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("PRODUTO")
public class Produto extends Item {


    private Integer estoque;
    @Column(precision = 10, scale = 4)
    private BigDecimal valorComprado;


    public Produto(ProdutoCadastroDTO dto){
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.valorEmPontos = dto.valorEmPontos();
        this.estoque = dto.estoque();
        this.valorComprado = dto.valorComprado();
    }

    public void atualizarProduto(ProdutoAtualizarDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.preco() != null){
            this.preco = dto.preco();
        }
        if(dto.valorEmPontos() != null){
            this.valorEmPontos = dto.valorEmPontos();
        }
        if(dto.estoque() != null){
            this.estoque = dto.estoque();
        }
        if(dto.valorComprado() != null){
            this.valorComprado = dto.valorComprado();
        }
    }
}
