package com.retorno.review.repositories;

import com.retorno.review.entities.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Page<Aluno> findAll(Pageable pageable);
    Optional<Aluno> findByName(String name);

    @Query("SELECT a FROM Aluno a WHERE a.email = :email")
    Optional<Aluno> findByEmail(String email);
}
