package com.retorno.review.configs.security.repositories;

import com.retorno.review.configs.security.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
    UserDetails findByLogin(String login);
}
