package com.retorno.review.controllers;

import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.entities.Aluno;
import com.retorno.review.services.AlunoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(AlunoController.class)
public class AlunoTestControllerMockito {

    @Autowired
    private MockMvc mockMvc;

    // Ta apontando erro, mas é o IntelliJ que não está reconhecendo as dependências
    @MockBean
    private AlunoService service;

    @Test
    void deveRetornarAlunoPorId() throws Exception {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setName("João");
        aluno.setUsername("joao123");
        aluno.setEmail("joao@gmail.com");
        aluno.setPassword("123");
        aluno.setPasswordConfirm("123");

        when(service.getById(1L)).thenReturn(new AlunoDto(aluno));

        mockMvc.perform(get("/alunos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João"))
                .andExpect(jsonPath("$.username").value("joao123"))
                .andExpect(jsonPath("$.email").value("joao@gmail.com"));
    }
}
