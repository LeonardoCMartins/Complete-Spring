package com.retorno.review.configs.security.controllers;

import com.retorno.review.configs.security.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PessoaController {

    @GetMapping("/secret")
    public ResponseEntity getAllPessoas(){
        return ResponseEntity.ok("SOMENTE ADMINS PODEM ACESSAR ESSE ENDPOINT");
    }

    @GetMapping("/public")
    public ResponseEntity publico(@AuthenticationPrincipal OidcUser principal){
        return ResponseEntity.ok(String.format("%s, %s",
                principal.getAttribute("email"),
                principal.getAuthorities()
               ));
    }
}
