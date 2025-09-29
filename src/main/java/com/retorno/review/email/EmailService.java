package com.retorno.review.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine; // para processar o HTML com Thymeleaf

    @Value("${spring.mail.username}")
    private String remetente;

    public void enviarEmailConfirmacao(String destinatario, String nome, String linkConfirmacao) {
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            helper.setFrom(remetente);
            helper.setTo(destinatario);
            helper.setSubject("Confirme seu e-mail");

            // Contexto com as variáveis do template
            Context context = new Context();
            context.setVariable("nome", nome);
            context.setVariable("link", linkConfirmacao);

            // Processa o HTML
            String html = templateEngine.process("email-confirmacao", context);
            helper.setText(html, true); // true = HTML

            mailSender.send(mensagem);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
