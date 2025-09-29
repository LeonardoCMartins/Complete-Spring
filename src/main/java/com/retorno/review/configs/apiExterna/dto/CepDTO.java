package com.retorno.review.configs.apiExterna.dto;


public record CepDTO(
        String cep,
        String logadouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
