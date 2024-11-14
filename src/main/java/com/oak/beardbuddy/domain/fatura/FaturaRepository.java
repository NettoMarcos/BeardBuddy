package com.oak.beardbuddy.domain.fatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    @Query("SELECT SUM(f.valorFatura) FROM Fatura f WHERE f.dataPagamento BETWEEN :inicioMes AND :fimMes")
    Double somarValorFaturasMesAtual(@Param("inicioMes") Timestamp inicioMes, @Param("fimMes") Timestamp fimMes);

    @Query("SELECT SUM(f.valorFatura) FROM Fatura f WHERE f.tipo = :tipo AND f.dataPagamento BETWEEN :inicioMes AND :fimMes")
    Double somarValorFaturasPorTipoMesAtual(@Param("tipo") EnumTipo tipo, @Param("inicioMes") Timestamp inicioMes, @Param("fimMes") Timestamp fimMes);
}
