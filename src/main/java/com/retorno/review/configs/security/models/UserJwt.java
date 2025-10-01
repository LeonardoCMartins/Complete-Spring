package com.retorno.review.configs.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users_jwt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJwt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_jwtid", columnDefinition = "BINARY(16)")
    private UUID userJwtid;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_jwt_roles",
            joinColumns = @JoinColumn(name = "user_jwtid"),
            inverseJoinColumns = @JoinColumn(name = "role_jwtid")
    )
    private Set<RoleJwt> roles;

    public boolean isLoginCorrect(LoginRequest loginRequest, BCryptPasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}
