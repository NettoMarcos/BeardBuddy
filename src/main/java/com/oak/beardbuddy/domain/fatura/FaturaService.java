package com.oak.beardbuddy.domain.fatura;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteRepository;
import com.oak.beardbuddy.domain.produto.Produto;
import com.oak.beardbuddy.domain.produto.ProdutoRepository;
import com.oak.beardbuddy.domain.servico.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaturaService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ServicoRepository servicoRepository;


    public void atualizar(String cpf, Long idProdOrServ, EnumTipo tipo) {

        if (tipo.toString().equals("PRODUTO")){
            var produto = produtoRepository.getReferenceById(idProdOrServ);
            atualizarQtdProduto(produto);

            var cliente = clienteRepository.findByCpf();
            atualizarPontosCliente(produto.getPreco(), cliente);
        }else if(tipo.toString().equals("SERVICO")){
            var servico = servicoRepository.getReferenceById(idProdOrServ);
            var cliente = clienteRepository.findByCpf();

            atualizarPontosCliente(servico.getPreco(), cliente);
        }

    }

    private void atualizarQtdProduto(Produto produto) {
        produto.atualizarQuantidade(produto);
    }


    private void atualizarPontosCliente(Double preco, Cliente cliente) {

        Integer pontosGanhos = (int) (preco * 100);

        cliente.atualizarPontosGanhos(pontosGanhos);
    }
}
