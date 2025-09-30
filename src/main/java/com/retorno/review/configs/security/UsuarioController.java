package com.retorno.review.configs.security;

import com.retorno.review.configs.security.models.CreateUsuarioDto;
import com.retorno.review.configs.security.models.RoleUsuario;
import com.retorno.review.configs.security.models.Usuario;
import com.retorno.review.configs.security.repositories.RoleUsuarioRepository;
import com.retorno.review.configs.security.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleUsuarioRepository roleUsuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Criar usuário BASIC (público)
    @PostMapping
    public ResponseEntity<String> createUser (@RequestBody CreateUsuarioDto dto) {
        if (usuarioRepository.findByUsername(dto.username()).isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Usuário já existe");
        }

        // Busca ou cria role BASIC com prefixo ROLE_
        RoleUsuario basicRole = roleUsuarioRepository.findByName("ROLE_BASIC")
                .orElseGet(() -> roleUsuarioRepository.save(new RoleUsuario(null, "ROLE_BASIC")));

        Usuario user = new Usuario();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        usuarioRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário BASIC criado com sucesso");
    }

    // Criar usuário ADMIN (pode ser protegido ou usado só para testes)
    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(@RequestBody CreateUsuarioDto dto) {
        if (usuarioRepository.findByUsername(dto.username()).isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Usuário já existe");
        }

        RoleUsuario adminRole = roleUsuarioRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleUsuarioRepository.save(new RoleUsuario(null, "ROLE_ADMIN")));

        Usuario user = new Usuario();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(adminRole));

        usuarioRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário ADMIN criado com sucesso");
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Acesso livre 🚀";
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("Oi ADMIN!");
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listUsers() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
}