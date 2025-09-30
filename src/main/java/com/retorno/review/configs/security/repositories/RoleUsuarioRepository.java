package com.retorno.review.configs.security.repositories;

import com.retorno.review.configs.security.models.RoleUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleUsuarioRepository extends JpaRepository<RoleUsuario, Long> {
    Optional<RoleUsuario> findByName(String name);
}