package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.item.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<ProdutoDetalhesDTO> cadastrarProduto(@RequestBody ProdutoCadastroDTO dto, UriComponentsBuilder uriBuilder){

        Produto produto = produtoService.cadastrarProduto(dto);

        URI uri = uriBuilder.path("{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProdutoDetalhesDTO(produto));
    }

    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<List<ProdutoDetalhesDTO>> listarProdutos(){
        List<ProdutoDetalhesDTO> lista = produtoService.listarProdutos();

        return ResponseEntity.ok(lista);
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<ProdutoDetalhesDTO> buscarProdutoPorId(@PathVariable Long id){
        ProdutoDetalhesDTO produto = produtoService.buscarProdutoPorId(id);

        return ResponseEntity.ok(produto);
    }

    @PutMapping
    @RequestMapping("/atualizar")
    public ResponseEntity<ProdutoDetalhesDTO> atualizarProduto(@RequestBody ProdutoAtualizarDTO dto){
        Produto produto = produtoService.atualizarProduto(dto);
        return ResponseEntity.ok(new ProdutoDetalhesDTO(produto));
    }

    @DeleteMapping
    @RequestMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){

        produtoService.deletarProduto(id);

        return ResponseEntity.noContent().build();
    }


}
