package com.oak.beardbuddy.domain.fatura;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteDetalhesDTO;
import com.oak.beardbuddy.domain.itemVendido.ItemVendido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_FATURAS")
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataGeracao;

    @Column(name = "VALOR_TOTAL",precision = 10, scale = 4)
    private BigDecimal valorTotal;

    @Column(name = "LUCRO_TOTAL",precision = 10, scale = 4)
    private BigDecimal lucroTotal;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    @OneToMany(mappedBy = "fatura")
    private List<ItemVendido> itensVendidos;

    public Fatura(Cliente cliente){

        this.dataGeracao = LocalDateTime.now();
        if(cliente != null){
            this.cliente = cliente;
        }
        this.valorTotal = new BigDecimal("0.0");
    }

}
