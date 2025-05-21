package com.oak.beardbuddy.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@DiscriminatorColumn(name = "TIPO")
@Table(name = "TB_ITENS")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String nome;
    protected BigDecimal preco;
    @Column(precision = 10, scale = 4)
    protected Integer valorEmPontos;

}
