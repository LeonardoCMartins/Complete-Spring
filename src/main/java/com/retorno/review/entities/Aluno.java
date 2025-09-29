package com.retorno.review.entities;

import com.retorno.review.audit.Auditable;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@Entity
@Table(name = "tb_alunos")
@Data
@Getter
@AllArgsConstructor
public class Aluno extends Auditable {

    public Aluno() {
    }

    public Aluno(String name, String username, String email, String password, String passwordConfirm) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String passwordConfirm;

    private Boolean ativo = false;

    public Aluno(int i, String joão, String joao123, String mail, String number, String number1) {
    }

}
