package com.retorno.review.repositories;

import com.retorno.review.entities.Aluno;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository repository;

    @Test
    @DisplayName("Should return true for both methods")
    void deveSalvarEConsultarAlunoPorNome() {
        Aluno aluno = new Aluno();
        aluno.setName("AlunoTeste");
        aluno.setEmail("teste@gmail.com");
        aluno.setUsername("Aluno-teste");
        aluno.setPassword("123");
        aluno.setPasswordConfirm("123");
        repository.save(aluno);

        Optional<Aluno> encontrado = repository.findByName("AlunoTeste");

        assertTrue(encontrado.isPresent());
        assertEquals("AlunoTeste", encontrado.get().getName());
    }

    @Test
    @DisplayName("Should return false for both methods")
    void deveSalvarEConsultarAlunoPorNome2() {
        Aluno aluno = new Aluno();
        aluno.setName("AlunoTeste");
        aluno.setEmail("teste@gmail.com");
        aluno.setUsername("Aluno-teste");
        aluno.setPassword("123");
        aluno.setPasswordConfirm("123");
        //repository.save(aluno);

        Optional<Aluno> encontrado = repository.findByName("AlunoTeste");

        assertEquals(false, encontrado.isPresent());
    }

}