package com.retorno.review.services;

import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.entities.Aluno;
import com.retorno.review.repositories.AlunoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @Autowired
    @InjectMocks
    private AlunoService service;

    @Test
    @DisplayName("Should save Aluno")
    void saveAluno() {
        AlunoDto alunoDto = new AlunoDto("AlunoTeste", "Aluno-teste", "aluno@gmail.com", "123","123");
        Aluno aluno = new Aluno(alunoDto.name(),alunoDto.username(),alunoDto.email(),alunoDto.password(),alunoDto.passwordConfirm());

        when(repository.save(any())).thenReturn(aluno);
        AlunoDto resultado = service.saveAluno(alunoDto);

        assertNotNull(resultado);
        assertEquals("AlunoTeste", resultado.name());
        assertEquals("aluno@gmail.com", resultado.email());
    }

    @Test
    void getAll() {
        // Dado (Given) - criando uma lista de alunos fake
        Aluno aluno1 = new Aluno("João", "joao123", "joao@gmail.com", "123", "123");
        Aluno aluno2 = new Aluno("Maria", "maria123", "maria@gmail.com", "456", "456");
        List<Aluno> alunosFake = List.of(aluno1, aluno2);

        // Simula o comportamento do repository (mockado)
        when(repository.findAll()).thenReturn(alunosFake);

        // Quando (When) - chamamos o método real do service
        List<AlunoDto> resultado = service.getAll();

        // Então (Then) - verificamos se o resultado bate com o esperado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).name());
        assertEquals("Maria", resultado.get(1).name());
    }


    @Test
    void getById() {
        Aluno aluno1 = new Aluno("João", "joao123", "joao@gmail.com", "123", "123");
        aluno1.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(aluno1));

        AlunoDto resultado = service.getById(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.name());
    }
}