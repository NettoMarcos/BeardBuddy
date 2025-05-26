package com.oak.beardbuddy.domain.itemVendido;

import com.oak.beardbuddy.domain.cliente.Cliente;
import com.oak.beardbuddy.domain.cliente.ClienteDetalhesDTO;
import com.oak.beardbuddy.domain.cliente.ClienteRepository;
import com.oak.beardbuddy.domain.cliente.ClienteService;
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
import java.util.Optional;

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

    public List<ItemVendidoDetalheDTO> cadastrarItensVendidos(List<ItemVendidoCadastroDTO> itensCadastro, String cpf) {
        Cliente cliente = null;

        if(cpf != null){
            cliente = clienteRepository.findByCpf(cpf);
        }

        validarItens(itensCadastro);

        Fatura fatura = new Fatura(cliente);

        faturaRepository.save(fatura);

        List<ItemVendido> itensSalvos = processarItens(itensCadastro, fatura);

        return itensSalvos.stream().map(ItemVendidoDetalheDTO:: new).toList();


    }

    private void validarItens(List<ItemVendidoCadastroDTO> itensCadastro){
        for(ItemVendidoCadastroDTO dto: itensCadastro){
            if(dto.itemId() == null){
                throw new IllegalArgumentException("O ID do item não pode ser nulo");
            }
            Item item = itemRepository.findById(dto.itemId()).orElseThrow(() ->
                    new EntityNotFoundException("Item não encontrado"));

            if(item instanceof Produto produto){
                validarEstoque(produto, dto.quantidade());
            }
        }
    }

    private List<ItemVendido> processarItens (List<ItemVendidoCadastroDTO> itensCadastro, Fatura fatura){

        List<ItemVendido> itensSalvos = new ArrayList<>();

        for (ItemVendidoCadastroDTO dto : itensCadastro){

            Item item = itemRepository.findById(dto.itemId())
                    .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

            BigDecimal valortotal = item.getPreco().multiply(BigDecimal.valueOf(dto.quantidade()));

            BigDecimal lucroTotal;

            if(item instanceof Produto produto){
                atualizarEstoque(produto, dto.quantidade());
                 lucroTotal = valortotal.subtract(produto.getValorComprado().multiply(BigDecimal.valueOf(dto.quantidade())));
                produtoRepository.save(produto);
            }else{
                lucroTotal = valortotal;
            }

            ItemVendido itemVendido = new ItemVendido(dto.quantidade(),fatura, item, valortotal, lucroTotal);

            itensSalvos.add(itemVendidoRepository.save(itemVendido));
        }

        return itensSalvos;
    }

    private void validarEstoque(Produto produto, int quantidade) {
        if (produto.getEstoque() < quantidade) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }
    }

    private void atualizarEstoque(Produto produto, int quantidade) {
        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoRepository.save(produto);
    }

    public void deletarItemVendido(Long id){
        itemVendidoRepository.deleteById(id);
    }
}
