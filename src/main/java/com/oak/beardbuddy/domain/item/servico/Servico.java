package com.oak.beardbuddy.domain.item.servico;

import com.oak.beardbuddy.domain.item.Item;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;




@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("SERVICO")
public class Servico extends Item {

    public Servico(ServicoCadastroDTO dto){
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
        if(dto.valorEmPontos() != null){
            this.valorEmPontos = dto.valorEmPontos();
        }
    }
}