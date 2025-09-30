package com.retorno.review.services;

import com.retorno.review.dtos.UsuarioDto;
import com.retorno.review.configs.security.models.Usuario;
import com.retorno.review.configs.security.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDto post(UsuarioDto usuarioDto){
        Usuario usuario = new Usuario(usuarioDto.username(), usuarioDto.password());
        repository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public List<UsuarioDto> getAll(){
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(UsuarioDto::new).toList();
    }

    public UsuarioDto getById(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UsuarioDto(usuario);
    }

    public UsuarioDto update(Long id, UsuarioDto usuarioDto){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setUsername(usuarioDto.username());
        usuario.setPassword(usuarioDto.password());

        Usuario novoUsuario = repository.save(usuario);
        return new UsuarioDto(novoUsuario);
    }

    public void delete(Long id){
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        repository.delete(usuario);
    }

}
