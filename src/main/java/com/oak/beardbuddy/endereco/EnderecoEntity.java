package com.oak.beardbuddy.endereco;

public class EnderecoEntity {
    String logradouro;
    String bairro;
    String cep;
    String cidade;
    String uf;
    String complemento;
    String numero;
    public EnderecoEntity(EnderecoDTO dto) {
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.cep = dto.cep();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.complemento = dto.complemento();
        this.numero = dto.numero();
    }
}
