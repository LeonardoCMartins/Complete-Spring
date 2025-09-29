package com.retorno.review.dtos;

import com.retorno.review.entities.Aluno;

public record AlunoDto(String name, String username, String email, String password, String passwordConfirm) {
    public AlunoDto(Aluno aluno){
           this(aluno.getName(), aluno.getUsername(), aluno.getEmail(), aluno.getPassword(), aluno.getPasswordConfirm());
    }
}
