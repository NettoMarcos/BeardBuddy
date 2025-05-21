package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.fatura.Fatura;
import com.oak.beardbuddy.domain.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ITENS_VENDIDOS")
public class ItemVendido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FATURA_ID", referencedColumnName = "id")
    private Fatura fatura;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "id")
    private Item item;
    private Integer quantidade;
    @Column(name = "VALOR_TOTAL",precision = 10, scale = 4)
    private BigDecimal valorTotal;



    public ItemVendido(ItemVendidoCadastroDTO dto, Fatura fatura, Item item){
        this.fatura = fatura;
        this.item = item;
        this.quantidade = dto.quantidade();
        this.valorTotal = dto.valorTotal();
    }

}
