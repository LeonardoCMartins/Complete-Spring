package com.retorno.review.configs.security;

import com.retorno.review.configs.security.models.CreateUserJwtDto;
import com.retorno.review.configs.security.models.RoleJwt;
import com.retorno.review.configs.security.models.UserJwt;
import com.retorno.review.configs.security.repositories.RoleJwtRepository;
import com.retorno.review.configs.security.repositories.UserJwtRepository;
import com.retorno.review.entities.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
public class UserJwtController {

    @Autowired
    private UserJwtRepository userRepository;
    @Autowired
    private RoleJwtRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserJwtDto dto) {

        var basicRole = roleRepository.findByName("BASIC")
                .orElseGet(() -> roleRepository.save(new RoleJwt(null, "BASIC")));

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new UserJwt();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserJwt>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

}
