package com.retorno.review.dtos;

import com.retorno.review.configs.security.models.Usuario;

public record UsuarioDto(String username, String password) {

    public UsuarioDto(Usuario usuario){
        this(usuario.getUsername(), usuario.getPassword());
    }
}
