package com.oak.beardbuddy.domain.item.servico;

import com.oak.beardbuddy.domain.item.produto.Produto;
import com.oak.beardbuddy.domain.item.produto.ProdutoAtualizarDTO;
import com.oak.beardbuddy.domain.item.produto.ProdutoCadastroDTO;
import com.oak.beardbuddy.domain.item.produto.ProdutoDetalhesDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    @Autowired
    ServicoRepository servicoRepository;


    public Servico cadastrarServico(ServicoCadastroDTO dto) {

        Servico servico = new Servico(dto);

        servicoRepository.save(servico);

        return servico;
    }

    public List<ServicoDetalhesDTO> listarServico() {
        return servicoRepository.findAll().stream().map(ServicoDetalhesDTO::new).toList();
    }

    public ServicoDetalhesDTO buscarServicoPorId(Long id) {
        return servicoRepository.findById(id).map(ServicoDetalhesDTO::new).orElseThrow(() -> new EntityNotFoundException("Servico não encontrado"));
    }

    public Servico atualizarServico(ServicoAtualizarDTO dto) {
        Servico servico = servicoRepository.getReferenceById(dto.id());

        servico.atualizarServico(dto);

        return servico;
    }

    public void deletarServico(Long id) {
        servicoRepository.deleteById(id);
    }

}
