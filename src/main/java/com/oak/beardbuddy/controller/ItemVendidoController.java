package com.oak.beardbuddy.controller;


import com.oak.beardbuddy.domain.itemVendido.ItemVendidoCadastroDTO;
import com.oak.beardbuddy.domain.itemVendido.ItemVendidoDetalheDTO;
import com.oak.beardbuddy.domain.itemVendido.ItemVendidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item_vendido")
public class ItemVendidoController {

    @Autowired
    ItemVendidoService itemVendidoService;

    @PostMapping({"/cadastrar", "/cadastrar/{cpfCliente}"})
    public ResponseEntity<List<ItemVendidoDetalheDTO>> cadastrarItensVendidos(
            @RequestBody List<ItemVendidoCadastroDTO> dto, @PathVariable(value = "cpfCliente", required = false) String cpf){

        List<ItemVendidoDetalheDTO> lista = itemVendidoService.cadastrarItensVendidos(dto, cpf);

        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarItemVendido(@PathVariable Long id){

        itemVendidoService.deletarItemVendido(id);

        return ResponseEntity.noContent().build();
    }
}
