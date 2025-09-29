package com.retorno.review.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private String imagemUrl;

    @Column(nullable = false)
    private String nivel;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Boolean gratuito;

    private String idioma;

    @Column(nullable = false)
    private Integer duracaoHoras;

    private LocalDate dataPublicacao;

    private LocalDate dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "instrutorId")
    private Instrutor instrutor;
}
