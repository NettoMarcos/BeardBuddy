package com.oak.beardbuddy.domain.fatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface FaturaRepository extends JpaRepository<Fatura, Long> {

    @Query(value = """
        SELECT COALESCE(SUM(f.lucro_total), 0)
        FROM tb_faturas f
        WHERE EXTRACT(MONTH FROM f.data_geracao) = EXTRACT(MONTH FROM CURRENT_DATE)
          AND EXTRACT(YEAR FROM f.data_geracao) = EXTRACT(YEAR FROM CURRENT_DATE)
        """, nativeQuery = true)
    BigDecimal lucroTotalMes();
}
