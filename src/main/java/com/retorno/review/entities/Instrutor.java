package com.retorno.review.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_instrutor")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "instrutorId")
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String biografia;
    private String fotoUrl;

    @Column(unique = true)
    private String linkedin;

    @Column(nullable = false)
    private String areaEspecializacao;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "instrutor", fetch = FetchType.LAZY)
    private Set<Curso> cursos = new HashSet<>();

}

