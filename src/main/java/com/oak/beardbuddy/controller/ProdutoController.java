package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;
    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<ProdutoDetalhesDTO> cadastrarProduto(@RequestBody @Valid ProdutoCadastroDTO dto, UriComponentsBuilder uriBuilder){
        var produto = new Produto(dto);
        repository.save(produto);

        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProdutoDetalhesDTO(produto));
    }

    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<Page<ProdutoDetalhesDTO>> listarProduto(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagina){
        var page = repository.findAll(pagina).map(ProdutoDetalhesDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ProdutoDetalhesDTO> buscarPorId(@PathVariable Long id){
        var produto = repository.getReferenceById(id);

        return ResponseEntity.ok(new ProdutoDetalhesDTO(produto));
    }

    @GetMapping("/listar-tipo/{tipo}")
    public ResponseEntity<Page<ProdutoDetalhesDTO>> buscarPorTipo(@PathVariable EnumTipo tipo, @PageableDefault(size = 10, page = 0) Pageable pagina) {
        var page = repository.findByTipo(String.valueOf(tipo), pagina).map(ProdutoDetalhesDTO::new);
        return ResponseEntity.ok(page);
    }
}
