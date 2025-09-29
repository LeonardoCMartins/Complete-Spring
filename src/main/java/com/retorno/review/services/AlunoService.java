package com.retorno.review.services;

import com.retorno.review.configs.exceptions.AlunoException;
import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.email.EmailConfirmationToken;
import com.retorno.review.email.EmailConfirmationTokenRepository;
import com.retorno.review.email.EmailService;
import com.retorno.review.entities.Aluno;
import com.retorno.review.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlunoService{

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailConfirmationTokenRepository tokenRepository;

    @CachePut(value = "alunos", key = "#result.id")
    public AlunoDto saveAluno(AlunoDto alunoDto){
        Aluno aluno = new Aluno(alunoDto.name(), alunoDto.username(), alunoDto.email(), alunoDto.password(), alunoDto.passwordConfirm());
        repository.save(aluno);

        // Apenas enviando o email
        //emailService.enviarEmail(alunoDto.email(), "Usuário Criado", "Email Enviado");

        //Validação de Email - Cria o token
        String token = UUID.randomUUID().toString();
        EmailConfirmationToken confirmationToken = new EmailConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setAluno(aluno);
        confirmationToken.setDataCriacao(LocalDateTime.now());
        confirmationToken.setExpiracao(LocalDateTime.now().plusMinutes(20));

        tokenRepository.save(confirmationToken);

        //Mostrar o link
        String link = "http://localhost:8080/confirm?token=" + token;
        emailService.enviarEmailConfirmacao(
                aluno.getEmail(),
                aluno.getName(),
                link
        );

        return new AlunoDto(aluno);
    }

    public List<AlunoDto> getAll(){
        List<Aluno> alunos = repository.findAll();
        return alunos.stream().map(AlunoDto::new).toList();
    }

    public List<AlunoDto> getAllPage(int pagina, int itens){
        Page<Aluno> alunos = repository.findAll(PageRequest.of(pagina,itens));
        return alunos.getContent().stream().map(AlunoDto::new).toList();
    }

    @Cacheable(value = "alunos", key = "#id")
    public AlunoDto getById (Long id){
        if(id == null || id <= 0){
            throw new AlunoException("Digite umm ID válido");
        }
        Aluno aluno = repository.findById(id).orElseThrow(() -> new AlunoException("Não encontrado"));
        return new AlunoDto(aluno);
    }

    @CachePut(value = "alunos", key = "#result.id")
    public AlunoDto updateAluno (Long id, AlunoDto alunoDto){
        Aluno aluno = repository.findById(id).orElseThrow(() -> new AlunoException("Não encontrado"));
        aluno.setName(alunoDto.name());
        aluno.setUsername(alunoDto.username());
        aluno.setEmail(alunoDto.email());
        aluno.setPassword(alunoDto.password());
        aluno.setPasswordConfirm(alunoDto.passwordConfirm());

        Aluno newAluno = repository.save(aluno);
        return new AlunoDto(newAluno);
    }

    @CacheEvict(value = "alunos", key = "#id")
    public void delete (Long id){
        Aluno aluno = repository.findById(id).orElseThrow(() -> new AlunoException("Não encontrado"));;
        repository.delete(aluno);
    }

    public Integer qntdAlunos(){
        Integer quantidadeAlunos = repository.findAll().size();
        return  quantidadeAlunos;
    }

    // ESTÁ AQUI APENAS PARA TESTAR O JASPER REPORT, DPS MUDAR PARA USAR ALUNODTO
    public List<Aluno> reportAllAlunos() {
        return repository.findAll();
    }

    public Optional<AlunoDto> alunoFindEmail(String email){
        return repository.findByEmail(email).map(AlunoDto::new);
    }

}
