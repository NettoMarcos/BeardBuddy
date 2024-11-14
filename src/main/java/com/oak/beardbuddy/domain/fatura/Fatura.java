package com.oak.beardbuddy.domain.fatura;

import com.oak.beardbuddy.domain.produto.Produto;
import com.oak.beardbuddy.domain.servico.Servico;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "TB_FATURAS")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Fatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpfCliente;
    private Long id_venda;
    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;
    @Column(name = "DATA_PAGAMENTO")
    private Date dataPagamento;
    private Double valorFatura;
    private boolean pagoEmPontos;

    public Fatura(FaturaCadastroDTO dto, Double valorFatura) {
        this.cpfCliente = dto.cpfCliente();
        this.id_venda = dto.id_venda();
        this.tipo = dto.tipo();
        this.dataPagamento = new Date();
        this.valorFatura = pagoEmPontos ? 0.0 : valorFatura;
    }
}
