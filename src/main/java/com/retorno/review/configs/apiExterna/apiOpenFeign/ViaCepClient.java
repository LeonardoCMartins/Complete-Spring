package com.retorno.review.configs.apiExterna.apiOpenFeign;

import com.retorno.review.configs.apiExterna.dto.CepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    CepDTO buscarCep(@PathVariable("cep") String cep);
}
