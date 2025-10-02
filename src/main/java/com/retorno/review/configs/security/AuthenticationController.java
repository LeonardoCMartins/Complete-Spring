package com.retorno.review.configs.security;

import com.retorno.review.configs.security.models.AuthenticationDto;
import com.retorno.review.configs.security.models.Pessoa;
import com.retorno.review.configs.security.models.RegisterDto;
import com.retorno.review.configs.security.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private PessoaRepository repository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = jwtService.generateToken(auth);
        return ResponseEntity.ok(Map.of("access_token", token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto data){
        if(this.repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Pessoa user = new Pessoa(data.login(), encryptedPassword, data.role());

        this.repository.save(user);

        return ResponseEntity.ok("Registrado");
    }
}

