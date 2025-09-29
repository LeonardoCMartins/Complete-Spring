package com.retorno.review.controllers;

import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.entities.Aluno;
import com.retorno.review.repositories.AlunoRepository;
import com.retorno.review.services.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

    @Mock
    private AlunoService service;

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoController controller;

    @Test
    @DisplayName("Test test")
    void save() {
        Aluno aluno = new Aluno("João", "joao123", "joao@gmail.com", "123", "123");
        AlunoDto alunoDto = new AlunoDto(aluno.getName(), aluno.getUsername(), aluno.getEmail(), aluno.getPassword(), aluno.getPasswordConfirm());

        when(service.saveAluno(any())).thenReturn(alunoDto);

        ResponseEntity<AlunoDto> response = controller.save(alunoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("João", response.getBody().name());

        verify(service, times(1)).saveAluno(any());
    }

    @Test
    void getAll() {
        Aluno aluno1 = new Aluno("João", "joao123", "joao@gmail.com", "123", "123");
        Aluno aluno2 = new Aluno("Maria", "maria123", "maria@gmail.com", "123", "123");
        List<Aluno> alunos = List.of(aluno1, aluno2);

        List<AlunoDto> alunosDto = alunos.stream().map(AlunoDto::new).toList();

        when(service.getAll()).thenReturn(alunosDto);

        ResponseEntity<List<AlunoDto>> response = controller.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("João", response.getBody().get(0).name());
        assertEquals("Maria", response.getBody().get(1).name());
    }

    @Test
    void getById() {
        Aluno aluno1 = new Aluno("João", "joao123", "joao@gmail.com", "123", "123");
        aluno1.setId(1L);

        when(service.getById(1L)).thenReturn(new AlunoDto(aluno1));

        ResponseEntity<AlunoDto> response = controller.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(aluno1.getName(), response.getBody().name());
    }

}

