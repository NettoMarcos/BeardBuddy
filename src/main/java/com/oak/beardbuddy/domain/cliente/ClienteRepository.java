package com.oak.beardbuddy.domain.cliente;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    ClienteDetalhesDTO findByCpf(String cpf);
}
