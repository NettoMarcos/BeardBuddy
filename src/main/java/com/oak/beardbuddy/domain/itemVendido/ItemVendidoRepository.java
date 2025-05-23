package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.fatura.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendidoRepository extends JpaRepository<ItemVendido, Long> {

    void deleteAllByFatura(Fatura fatura);
}
