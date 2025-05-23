package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.fatura.Fatura;
import com.oak.beardbuddy.domain.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Item item;
    private String nomeItem;
    private BigDecimal precoUnitario;
    private Integer quantidade;
    @Column(precision = 10, scale = 4)
    private BigDecimal lucroTotal;
    @Column(name = "VALOR_TOTAL",precision = 10, scale = 4)
    private BigDecimal valorTotal;



    public ItemVendido( Integer quantidade, Fatura fatura, Item item, BigDecimal valorTotal, BigDecimal lucroTotal ){
        this.fatura = fatura;
        this.item = item;
        this.nomeItem = item.getNome();
        this.precoUnitario = item.getPreco();
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.lucroTotal = lucroTotal;
    }

}
