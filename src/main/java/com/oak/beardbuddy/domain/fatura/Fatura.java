package com.oak.beardbuddy.domain.fatura;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "TB_FATURA")
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
    private Long idProdOrServ;
    @Enumerated(EnumType.STRING)
    private EnumTipo tipo;
    private Date dataPagamento;

    public Fatura(FaturaCadastroDTO dto) {
        this.cpfCliente = dto.cpfCliente();
        this.idProdOrServ = dto.idProdOrServ();
        this.tipo = dto.tipo();
        this.dataPagamento = new Date();
    }
}
