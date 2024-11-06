package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.fatura.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fatura")
public class FaturaController {

    @Autowired
    private FaturaRepository repository;

    @Autowired
    private FaturaService service;

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<List<FaturaDetalhesDTO>> cadastrarFatura(@RequestBody @Valid List<FaturaCadastroDTO> dtos , UriComponentsBuilder uriBuilder){

        List<FaturaDetalhesDTO> faturasCriadas = new ArrayList<>();
        for(FaturaCadastroDTO dto : dtos ){
            var fatura = new Fatura(dto);


            service.atualizar(dto.cpfCliente(), dto.idProdOrServ(), dto.tipo());

            repository.save(fatura);
            faturasCriadas.add(new FaturaDetalhesDTO(fatura));

        }
        var uri = uriBuilder.path("/faturas").build().toUri();
        return ResponseEntity.created(uri).body(faturasCriadas);
    }

}
