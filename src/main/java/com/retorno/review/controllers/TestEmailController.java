package com.retorno.review.controllers;

import com.retorno.review.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/test-email")
    public String testEmail() {
        //emailService.enviarEmail("hjtemfla123@gmail.com", "Assunto", "Corpo do e-mail");
        return "Email enviado (verifique caixa de entrada)";
    }
}

