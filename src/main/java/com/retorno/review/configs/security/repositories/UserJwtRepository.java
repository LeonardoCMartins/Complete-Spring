package com.retorno.review.configs.security.repositories;

import com.retorno.review.configs.security.models.UserJwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJwtRepository extends JpaRepository<UserJwt, UUID> {
    Optional<UserJwt> findByUsername(String username);
}
