package com.oak.beardbuddy.domain.fatura;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteRepository;
import com.oak.beardbuddy.domain.produto.Produto;
import com.oak.beardbuddy.domain.produto.ProdutoRepository;
import com.oak.beardbuddy.domain.servico.Servico;
import com.oak.beardbuddy.domain.servico.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class FaturaService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ServicoRepository servicoRepository;


    @Transactional
    public void atualizar(String cpfCliente, Long idProdOrServ, EnumTipo tipo) {
        Cliente cliente = clienteRepository.findByCpf(cpfCliente);

        if (tipo == EnumTipo.PRODUTO) {
            Produto produto = produtoRepository.findById(idProdOrServ).orElse(null);
            if (produto != null){
                atualizarQtdProduto(produto);
                atualizarPontosCliente(produto.getPreco(), cliente);
            }
        } else if (tipo == EnumTipo.SERVICO) {

            servicoRepository.findById(idProdOrServ).ifPresent(servico -> atualizarPontosCliente(servico.getPreco(), cliente));
        }
    }

    private void atualizarQtdProduto(Produto produto) {
        produto.atualizarQuantidade(produto);
    }


    private void atualizarPontosCliente(Double preco, Cliente cliente) {

        Integer pontosGanhos = (int) (preco * 100);
        System.out.println(pontosGanhos);
        cliente.atualizarPontosGanhos(pontosGanhos);
    }

    public Timestamp calcularFimMes(LocalDate dataAtual) {

        LocalDate ultimoDiaMes = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());

        return Timestamp.valueOf(ultimoDiaMes.atTime(23, 59, 59));
    }

    public Timestamp calcularPrimeiroDiaMes(LocalDate dataAtual){
        LocalDate primeiroDiaMes = dataAtual.withDayOfMonth(1);

        return Timestamp.valueOf(primeiroDiaMes.atStartOfDay());
    }

    public Double obterPrecoVenda(Long idVenda, EnumTipo tipo) {
        if (tipo == EnumTipo.PRODUTO) {
            Produto produto = produtoRepository.findById(idVenda)
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
            return produto.getPreco();  // Retorna o preço do produto
        } else if (tipo == EnumTipo.SERVICO) {
            Servico servico = servicoRepository.findById(idVenda)
                    .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado"));
            return servico.getPreco();  // Retorna o preço do serviço
        }
        throw new IllegalArgumentException("Tipo de venda inválido");
    }
}
