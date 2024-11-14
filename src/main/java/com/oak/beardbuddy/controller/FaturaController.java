package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.fatura.*;
import com.oak.beardbuddy.domain.produto.ProdutoRepository;
import com.oak.beardbuddy.domain.servico.ServicoRepository;
import com.oak.beardbuddy.infra.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fatura")
public class FaturaController {

    @Autowired
    private FaturaRepository repository;

    @Autowired
    private FaturaService service;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @PostMapping
    @RequestMapping("/cadastrar")
    public ResponseEntity<List<FaturaDetalhesDTO>> cadastrarFatura(@RequestBody @Valid List<FaturaCadastroDTO> dtos , UriComponentsBuilder uriBuilder){

        List<FaturaDetalhesDTO> faturasCriadas = new ArrayList<>();

        for(FaturaCadastroDTO dto : dtos) {
            Double precoVenda = service.obterPrecoVenda(dto.id_venda(), dto.tipo());

            Fatura fatura = new Fatura(dto, precoVenda);
            service.atualizar(dto.cpfCliente(), dto.id_venda(), dto.tipo());

            repository.save(fatura);

            faturasCriadas.add(new FaturaDetalhesDTO(fatura));
        }
        var uri = uriBuilder.path("/faturas").build().toUri();

        return ResponseEntity.created(uri).body(faturasCriadas);
    }

    @GetMapping
    @RequestMapping("/listar")
    public ResponseEntity<Page<FaturaDetalhesDTO>> listarProduto(@PageableDefault(size = 10, page = 0, sort = {"id"}) Pageable pagina){
        var page = repository.findAll(pagina).map(FaturaDetalhesDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<FaturaDetalhesDTO> buscarPorId(@PathVariable Long id){
        var fatura = repository.getReferenceById(id);

        return ResponseEntity.ok(new FaturaDetalhesDTO(fatura));
    }

    @DeleteMapping
    @RequestMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<?> excluirFatura(@PathVariable Long id){
        Fatura fatura = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fatura com ID " + id + " não foi encontrada."));
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @RequestMapping("/total-mes")
    public ResponseEntity<FaturaValorTotalDTO> getValorTotalFaturasMesAtual() {
        LocalDate dataAtual = LocalDate.now();

        Timestamp inicioMesTimestamp = service.calcularPrimeiroDiaMes(dataAtual);
        Timestamp fimMesTimestamp = service.calcularFimMes(dataAtual);

        Double valorTotal = repository.somarValorFaturasMesAtual(inicioMesTimestamp, fimMesTimestamp);


        return ResponseEntity.ok(new FaturaValorTotalDTO(valorTotal));
    }
    @GetMapping
    @RequestMapping("/total-mes/produto")
    public ResponseEntity<FaturaValorTotalDTO> getValorTotalFaturasProdutoMesAtual() {
        LocalDate dataAtual = LocalDate.now();

        Timestamp inicioMesTimestamp = service.calcularPrimeiroDiaMes(dataAtual);
        Timestamp fimMesTimestamp = service.calcularFimMes(dataAtual);

        Double valorTotalProduto = repository.somarValorFaturasPorTipoMesAtual(EnumTipo.PRODUTO, inicioMesTimestamp, fimMesTimestamp);
        return ResponseEntity.ok(new FaturaValorTotalDTO(valorTotalProduto));
    }
    @GetMapping
    @RequestMapping("/total-mes/servico")
    public ResponseEntity<FaturaValorTotalDTO> getValorTotalFaturasServicoMesAtual() {
        LocalDate dataAtual = LocalDate.now();

        Timestamp inicioMesTimestamp = service.calcularPrimeiroDiaMes(dataAtual);
        Timestamp fimMesTimestamp = service.calcularFimMes(dataAtual);

        Double valorTotalServico = repository.somarValorFaturasPorTipoMesAtual(EnumTipo.SERVICO, inicioMesTimestamp, fimMesTimestamp);
        return ResponseEntity.ok(new FaturaValorTotalDTO(valorTotalServico));
    }
}
