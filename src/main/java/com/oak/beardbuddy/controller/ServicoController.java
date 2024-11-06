package com.oak.beardbuddy.controller;


import com.oak.beardbuddy.domain.produto.ProdutoDetalhesDTO;
import com.oak.beardbuddy.domain.servico.*;
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
@RequestMapping("servico")
public class ServicoController {
    @Autowired
    private ServicoRepository repository;
    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<ServicoDetalhesDTO> cadastrarServico(@RequestBody @Valid ServicoCadastroDTO dto, UriComponentsBuilder uriBuilder){
        var servico = new Servico(dto);
        repository.save(servico);

        var uri = uriBuilder.path("/servico/{id}").buildAndExpand(servico.getId()).toUri();

        return ResponseEntity.created(uri).body(new ServicoDetalhesDTO(servico));
    }

   @GetMapping
   @RequestMapping("/listar")
   public ResponseEntity<Page<ServicoDetalhesDTO>> listarServico(@PageableDefault(size = 10, page = 0, sort = {"id"})Pageable pagina){
        var page = repository.findAll(pagina).map(ServicoDetalhesDTO::new);

        return ResponseEntity.ok(page);
   }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<ServicoDetalhesDTO> buscarPorId(@PathVariable Long id){
        var servico = repository.getReferenceById(id);

        return ResponseEntity.ok(new ServicoDetalhesDTO(servico));
    }

    @PutMapping
    @RequestMapping("/atualizar")
    @Transactional
    public ResponseEntity<ServicoDetalhesDTO> atualizarProduto(@RequestBody @Valid ServicoAtualizarDTO dto){
        var servico = repository.getReferenceById(dto.id());
        servico.atualizarServico(dto);

        return ResponseEntity.ok(new ServicoDetalhesDTO(servico));
    }

    @DeleteMapping
    @RequestMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> excluirProduto(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
