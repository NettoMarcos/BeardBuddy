package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.fatura.FaturaDetalhesDTO;
import com.oak.beardbuddy.domain.fatura.FaturaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/fatura")
public class FaturaController {

    @Autowired
    FaturaService faturaService;

    @GetMapping("/listar")
    public ResponseEntity<List<FaturaDetalhesDTO>> listarFaturas(){

        List<FaturaDetalhesDTO> lista = faturaService.listarFaturas();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/lucro_total_mes")
    public ResponseEntity<BigDecimal> lucroTotalMes(){

        BigDecimal lucroTotalMes = faturaService.lucroTotalMes();

        return ResponseEntity.ok(lucroTotalMes);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFatura(@PathVariable Long id){
        faturaService.deletarFatura(id);

        return ResponseEntity.noContent().build();
    }

}
