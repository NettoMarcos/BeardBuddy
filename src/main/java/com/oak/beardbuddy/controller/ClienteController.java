package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<ClienteDetalhesDTO> cadastrarCliente(@RequestBody ClienteCadastroDTO dto, UriComponentsBuilder uriBuilder){
        var cliente = new Cliente(dto);
        repository.save(cliente);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new ClienteDetalhesDTO(cliente));
    }

    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<Page<ClienteListarDTO>> listarCliente(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pagina){
       var page = repository.findAll(pagina).map(ClienteListarDTO::new);

       return ResponseEntity.ok(page);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ClienteDetalhesDTO> buscarPorId(@PathVariable Long id){
        var cliente  = repository.getReferenceById(id);

        return ResponseEntity.ok(new ClienteDetalhesDTO(cliente));
    }

    @PutMapping
    @RequestMapping("/atualizar")
    @Transactional
    public ResponseEntity<ClienteDetalhesDTO> atualizarCliente(@RequestBody @Valid ClienteAtualizarDTO dto){
        var cliente = repository.getReferenceById(dto.id());
        cliente.atualizarCliente(dto);

        return ResponseEntity.ok(new ClienteDetalhesDTO(cliente));
    }

    @DeleteMapping
    @RequestMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}