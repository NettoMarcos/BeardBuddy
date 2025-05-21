package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.item.servico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<ServicoDetalhesDTO> cadastrarServico(@RequestBody ServicoCadastroDTO dto, UriComponentsBuilder uriBuilder){

        Servico servico = servicoService.cadastrarServico(dto);

        URI uri = uriBuilder.path("{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ServicoDetalhesDTO(servico));
    }

    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<List<ServicoDetalhesDTO>> listarServico(){
        List<ServicoDetalhesDTO> lista = servicoService.listarServico();

        return ResponseEntity.ok(lista);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<ServicoDetalhesDTO> buscarServicoPorId(@PathVariable Long id){
        ServicoDetalhesDTO servico = servicoService.buscarServicoPorId(id);

        return ResponseEntity.ok(servico);
    }

    @PutMapping
    @RequestMapping("/atualizar")
    public ResponseEntity<ServicoDetalhesDTO> atualizarServico(@RequestBody ServicoAtualizarDTO dto){
        Servico servico = servicoService.atualizarServico(dto);

        return ResponseEntity.ok(new ServicoDetalhesDTO(servico));
    }

    @DeleteMapping
    @RequestMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id){

        servicoService.deletarServico(id);

        return ResponseEntity.noContent().build();
    }
}
