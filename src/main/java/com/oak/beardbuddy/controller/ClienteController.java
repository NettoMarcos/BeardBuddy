package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<ClienteDetalhesDTO> cadastrarCliente(@RequestBody @Valid CLienteCadastrarDTO dto,
                                                                UriComponentsBuilder uriBuilder){
        Cliente cliente = clienteService.cadastrarCliente(dto);

        var uri = uriBuilder.path("/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDetalhesDTO(cliente));
    }

    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<List<ClienteDetalhesDTO>> listarCliente(){
        List<ClienteDetalhesDTO> lista = clienteService.listarCliente();

        return ResponseEntity.ok(lista);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ClienteDetalhesDTO> buscarClientePorId(@PathVariable Long id){
        ClienteDetalhesDTO cliente = clienteService.buscarClientePorId(id);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping
    @RequestMapping("buscarPorCpf/{cpf}")
    public ResponseEntity<ClienteDetalhesDTO> buscarClientePorCpf(@PathVariable String cpf){
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);

        return ResponseEntity.ok(new ClienteDetalhesDTO(cliente));
    }

    @PutMapping
    @RequestMapping("/atualizar")
    @Transactional
    public ResponseEntity<ClienteDetalhesDTO> atualizarCliente(@RequestBody @Valid ClienteAtualizarDTO dto){

        Cliente cliente = clienteService.atualizarCliente(dto);

        return ResponseEntity.ok(new ClienteDetalhesDTO(cliente));
    }

    @DeleteMapping
    @RequestMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){

        clienteService.deletarCliente(id);

        return ResponseEntity.noContent().build();
    }
}
