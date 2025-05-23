package com.oak.beardbuddy.domain.fatura;


import com.oak.beardbuddy.domain.itemVendido.ItemVendidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FaturaService {

    @Autowired
    FaturaRepository faturaRepository;

    @Autowired
    ItemVendidoRepository itemVendidoRepository;


    public List<FaturaDetalhesDTO> listarFaturas() {
        return faturaRepository.findAll().stream().map(FaturaDetalhesDTO::new).toList();
    }

    @Transactional
    public void deletarFatura(Long id) {

        Fatura fatura = faturaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Fatura não encontrado"));
        itemVendidoRepository.deleteAllByFatura(fatura);
        faturaRepository.deleteById(id);
    }

    public BigDecimal lucroTotalMes() {

        return  faturaRepository.lucroTotalMes();
    }
}
