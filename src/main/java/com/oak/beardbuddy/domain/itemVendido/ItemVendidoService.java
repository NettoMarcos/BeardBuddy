package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteRepository;
import com.oak.beardbuddy.domain.fatura.Fatura;
import com.oak.beardbuddy.domain.fatura.FaturaRepository;
import com.oak.beardbuddy.domain.item.Item;
import com.oak.beardbuddy.domain.item.ItemRepository;
import com.oak.beardbuddy.domain.item.produto.Produto;
import com.oak.beardbuddy.domain.item.produto.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
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
        for (ItemVendidoCadastroDTO dto : itensCadastro){
            if (dto.itemId() == null) {
                throw new IllegalArgumentException("O ID do item não pode ser nulo");
            }
            itemRepository.findById(dto.itemId())
                    .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
        }

        Fatura fatura = new Fatura(cliente);

        faturaRepository.save(fatura);

        List<ItemVendido> itensSalvos = new ArrayList<>();

        BigDecimal valortotal = BigDecimal.ZERO;

        for (ItemVendidoCadastroDTO dto : itensCadastro){

            if (dto.itemId() == null) {
                throw new IllegalArgumentException("O ID do item não pode ser nulo");
            }

            Item item = itemRepository.findById(dto.itemId())
                    .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

            valortotal = valortotal.add(item.getPreco().multiply(BigDecimal.valueOf(dto.quantidade())));

            if(item instanceof Produto produto){
                if (produto.getEstoque() < dto.quantidade()){
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
                }
                produto.setEstoque(produto.getEstoque() - dto.quantidade());
                valortotal = valortotal.subtract(produto.getValorComprado().multiply(BigDecimal.valueOf(dto.quantidade())));
                produtoRepository.save(produto);
            }

            ItemVendido itemVendido = new ItemVendido(dto.quantidade(),fatura, item, valortotal);

            itensSalvos.add(itemVendidoRepository.save(itemVendido));
        }

        return itensSalvos.stream().map(ItemVendidoDetalheDTO:: new).toList();


    }
}
