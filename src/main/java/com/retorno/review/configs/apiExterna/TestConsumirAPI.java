package com.retorno.review.configs.apiExterna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestConsumirAPI {

    @Autowired
    private TesteService testeService;

    /*@GetMapping("/{cep}")
    public String cep (@PathVariable String cep){
        testeService.salvardados(cep);
        return "dados salvos";
    }*/

    @GetMapping("/cep/{cep}")
    ResponseEntity dadosOpen(@PathVariable("cep") String cep){
        testeService.salvarDadosOpenFeign(cep);
        return ResponseEntity.status(HttpStatus.OK).body("dados salvos");
    }
}
