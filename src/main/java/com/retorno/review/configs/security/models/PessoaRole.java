package com.retorno.review.configs.security.models;

import lombok.Getter;

@Getter
public enum PessoaRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    PessoaRole(String role) {
        this.role = role;
    }
}
