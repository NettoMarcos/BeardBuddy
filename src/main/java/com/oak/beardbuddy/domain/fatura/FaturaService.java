package com.oak.beardbuddy.domain.fatura;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteRepository;
import com.oak.beardbuddy.domain.produto.Produto;
import com.oak.beardbuddy.domain.produto.ProdutoDetalhesDTO;
import com.oak.beardbuddy.domain.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaturaService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ClienteRepository clienteRepository;


    public void atualizar(String cpf, Long idProdOrServ, EnumTipo tipo) {

        if (tipo.toString() == "PRODUTO"){
            var produto = produtoRepository.getReferenceById(idProdOrServ);
            atualizarQtdProduto(produto);

            var cliente = clienteRepository.findByCpf();
            atualizarPontosCliente(produto, cliente);
        }

    }

    private void atualizarQtdProduto(Produto produto) {
        produto.atualizarQuantidade(produto);
    }


    private void atualizarPontosCliente(Produto produto, Cliente cliente) {
        Double preco = produto.getPreco();
        Integer pontosGanhos = (int) (preco * 100);

        cliente.atualizarPontosGanhos(cliente,pontosGanhos);
    }
}
