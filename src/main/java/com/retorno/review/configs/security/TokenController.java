package com.retorno.review.configs.security;

import com.retorno.review.configs.security.models.LoginRequest;
import com.retorno.review.configs.security.models.LoginResponse;
import com.retorno.review.configs.security.models.RoleJwt;
import com.retorno.review.configs.security.models.UserJwt;
import com.retorno.review.configs.security.repositories.UserJwtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class TokenController {

    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UserJwtRepository repository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var user = repository.findByUsername(loginRequest.username());

        // Validação se o usuário existe para o login e se a senha ta correta
        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("usuário ou senha inválidos pae");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.get().getRoles()
                .stream().map(RoleJwt::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend") // quem ta gerando o token
                .subject(user.get().getUsername()) //qual é o usuário
                .issuedAt(now) // tempo de criação do token
                .expiresAt(now.plusSeconds(expiresIn)) // tempo de expiração do token
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue,expiresIn));
    }
}
