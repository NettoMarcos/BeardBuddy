package com.oak.beardbuddy.controller;

import com.oak.beardbuddy.domain.usuario.AutenticacaoDTO;
import com.oak.beardbuddy.domain.usuario.Usuario;
import com.oak.beardbuddy.infra.security.TokenJWT_DTO;
import com.oak.beardbuddy.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> efetuarLogin(@RequestBody @Validated AutenticacaoDTO dto){
        var authenticationTokentoken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = manager.authenticate(authenticationTokentoken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenJWT_DTO(tokenJWT));
    }
}