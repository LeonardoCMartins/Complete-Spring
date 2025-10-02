package com.retorno.review.configs.security.models;

public record RegisterDto(String login, String password, PessoaRole role) {
}
