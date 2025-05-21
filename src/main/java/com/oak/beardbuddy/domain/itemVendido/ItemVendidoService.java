package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteRepository;
import com.oak.beardbuddy.domain.fatura.Fatura;
import com.oak.beardbuddy.domain.fatura.FaturaRepository;
import com.oak.beardbuddy.domain.item.Item;
import com.oak.beardbuddy.domain.item.ItemRepository;
import com.oak.beardbuddy.domain.item.produto.Produto;
import com.oak.beardbuddy.domain.item.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemVendidoService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    FaturaRepository faturaRepository;

    @Autowired
    ItemVendidoRepository itemVendidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public List<ItemVendidoDetalheDTO> cadastrarItensVendidos(List<ItemVendidoCadastroDTO> itensCadastro, Long idCliente) {
        Cliente cliente = null;
        if(idCliente != null){
             cliente = clienteRepository.getReferenceById(idCliente);
        }

        Fatura fatura = new Fatura(cliente);

        faturaRepository.save(fatura);

        List<ItemVendido> itensSalvos = new ArrayList<>();

        for (ItemVendidoCadastroDTO dto : itensCadastro){
            Item item = dto.Item();

            if(item instanceof Produto produto){
                if (produto.getEstoque() < dto.quantidade()){
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
                }
                produto.setEstoque(produto.getEstoque() - dto.quantidade());
                produtoRepository.save(produto);
            }

            ItemVendido itemVendido = new ItemVendido(dto,fatura, item);

            itensSalvos.add(itemVendidoRepository.save(itemVendido));
        }

        return itensSalvos.stream().map(ItemVendidoDetalheDTO:: new).toList();


    }
}
