package com.oak.beardbuddy.domain.cliente;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    public Cliente cadastrarCliente(CLienteCadastrarDTO dto) {
        Cliente cliente = new Cliente(dto);

        clienteRepository.save(cliente);

        return cliente;
    }

    public Cliente atualizarCliente(ClienteAtualizarDTO dto) {
        Cliente cliente = clienteRepository.getReferenceById(dto.id());

        cliente.atualizarCliente(dto);

        return cliente;
    }

    public List<ClienteDetalhesDTO> listarCliente() {
        return clienteRepository.findAll().stream().map(ClienteDetalhesDTO::new).toList();
    }

    public ClienteDetalhesDTO buscarClientePorId(Long id) {
        return clienteRepository.findById(id).map(ClienteDetalhesDTO::new).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Cliente buscarClientePorCpf(String cpf) {

        return clienteRepository.findByCpf(cpf);
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}