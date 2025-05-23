package com.oak.beardbuddy.domain.item.produto;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;
    public Produto cadastrarProduto(ProdutoCadastroDTO dto) {

        Produto produto = new Produto(dto);

        produtoRepository.save(produto);

        return produto;
    }

    public List<ProdutoDetalhesDTO> listarProdutos() {
        return produtoRepository.findAll().stream().map(ProdutoDetalhesDTO::new).toList();
    }

    public ProdutoDetalhesDTO buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).map(ProdutoDetalhesDTO::new).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    public Produto atualizarProduto(ProdutoAtualizarDTO dto) {
        Produto produto = produtoRepository.findById(dto.id())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.atualizarProduto(dto);

        produtoRepository.save(produto);

        return produto;
    }

    public void deletarProduto(Long id) {


        produtoRepository.deleteById(id);
    }
}
