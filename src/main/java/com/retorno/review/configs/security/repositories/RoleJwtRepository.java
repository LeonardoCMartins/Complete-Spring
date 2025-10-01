package com.retorno.review.configs.security.repositories;

import com.retorno.review.configs.security.models.RoleJwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJwtRepository extends JpaRepository<RoleJwt, Long> {
    Optional<RoleJwt> findByName(String name);
}
