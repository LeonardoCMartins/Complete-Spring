package com.retorno.review.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/oi")
    public String oi(@RequestParam String name, Locale locale){
        return messageSource.getMessage("hello", new Object[]{name}, locale);
    }

    @GetMapping("/greet")
    public String greet(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);
    }

    @GetMapping("/farewell")
    public String farewell(Locale locale) {
        return messageSource.getMessage("farewell", null, locale);
    }

    @GetMapping("/account")
    public String accountStatus(@RequestParam String name, @RequestParam double balance, @RequestParam String status, Locale locale){
        return messageSource.getMessage("account.status", new Object[]{name,balance,status}, locale);
    }
}
