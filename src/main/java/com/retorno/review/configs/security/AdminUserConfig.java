package com.retorno.review.configs.security;

import com.retorno.review.configs.security.models.RoleJwt;
import com.retorno.review.configs.security.models.UserJwt;
import com.retorno.review.configs.security.repositories.RoleJwtRepository;
import com.retorno.review.configs.security.repositories.UserJwtRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RoleJwtRepository roleJwtRepository;
    @Autowired
    private UserJwtRepository userJwtRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        RoleJwt roleAdmin = roleJwtRepository.findByName(RoleJwt.Values.ADMIN.name())
                .orElseGet(() -> roleJwtRepository.save(new RoleJwt(null, RoleJwt.Values.ADMIN.name())));

        var userAdmin = userJwtRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(user -> {
            System.out.println("admin já existe");
        }, () -> {
            var user = new UserJwt();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("123"));
            user.setRoles(Set.of(roleAdmin));
            userJwtRepository.save(user);
        });
    }
}
