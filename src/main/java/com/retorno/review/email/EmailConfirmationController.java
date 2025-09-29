package com.retorno.review.email;

import com.retorno.review.entities.Aluno;
import com.retorno.review.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class EmailConfirmationController {

    @Autowired
    private EmailConfirmationTokenRepository tokenRepo;

    @Autowired
    private AlunoRepository alunoRepo;

    @GetMapping("/confirm")
    public String confirmarEmail(@RequestParam String token) {
        Optional<EmailConfirmationToken> optional = tokenRepo.findByToken(token);

        if (optional.isEmpty()) {
            return "Token inválido.";
        }

        EmailConfirmationToken confToken = optional.get();

        if (confToken.getExpiracao().isBefore(LocalDateTime.now())) {
            return "Token expirado.";
        }

        Aluno aluno = confToken.getAluno();
        aluno.setAtivo(true);
        alunoRepo.save(aluno);

        return "E-mail confirmado com sucesso!";
    }
}
