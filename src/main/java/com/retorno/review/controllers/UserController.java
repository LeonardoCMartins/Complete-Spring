package com.retorno.review.controllers;

import com.retorno.review.entities.Role;
import com.retorno.review.entities.User;
import com.retorno.review.repositories.RoleRepository;
import com.retorno.review.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;


//    @Transactional
//    @PostMapping
//    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {
//        var basicRole = roleRepository.findByName("ROLE_BASIC")
//                .orElseGet(() -> roleRepository.save(new Role("ROLE_BASIC")));
//
//        if (userRepository.findByUsername(dto.username()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
//        }
//
//        var user = new User();
//        user.setUsername(dto.username());
//        user.setPassword(passwordEncoder.encode(dto.password()));
//        user.setRoles(Set.of(basicRole));
//
//        userRepository.save(user);
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/test")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> onlyAdmin(){
//        return ResponseEntity.ok("OI ADMIN");
//    }

//    @GetMapping("/list")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<User>> listUsers() {
//        return ResponseEntity.ok(userRepository.findAll());
//    }
}
