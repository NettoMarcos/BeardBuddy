package com.oak.beardbuddy.domain.produto;

import com.oak.beardbuddy.domain.cliente.ClienteCadastroDTO;
import com.oak.beardbuddy.infra.exception.ProdutoForaDeEstoqueException;
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
    private Double preco;
    private Integer quantidade;
    private Integer valorEmPontos;



    public Produto(ProdutoCadastroDTO dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
        if (dto.quantidade() != null){
            this.quantidade = dto.quantidade();
        } else{
            this.quantidade = 0;
        }
        this.valorEmPontos = dto.valorEmPontos();

    }

    public void atualizarProduto(ProdutoAtualizarDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.preco() != null){
            this.preco = dto.preco();
        }
        if (dto.valorEmPontos() != null){
            this.valorEmPontos = dto.valorEmPontos();
        }
    }

    public void atualizarQuantidade(Produto produto ) {
        if (quantidade <= 0) {
            throw new ProdutoForaDeEstoqueException("Produto " + produto.getNome() + " está fora de estoque.");
        }
        this.quantidade -= 1;
    }
}
