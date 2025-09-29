package com.retorno.review.email;

import com.retorno.review.entities.Aluno;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EmailConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    private Aluno aluno;

    private LocalDateTime dataCriacao;
    private LocalDateTime expiracao;

}
