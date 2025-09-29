package com.retorno.review.configs.apiExterna;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cep;
    private String bairro;
    private String localidade;
    private String uf;

}
