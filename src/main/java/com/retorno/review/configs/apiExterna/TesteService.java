package com.retorno.review.configs.apiExterna;

import com.retorno.review.configs.apiExterna.apiOpenFeign.ViaCepClient;
import com.retorno.review.configs.apiExterna.dto.CepDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TesteService {

    private final TestRepository testRepository;
    private final ViaCepClient viaCepClient;

    public TesteService(TestRepository testRepository, ViaCepClient viaCepClient) {
        this.testRepository = testRepository;
        this.viaCepClient = viaCepClient;
    }

    /*public void salvardados(String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);

        WebClient webClient = WebClient.create();
        String json = webClient
                            .get()
                            .uri(url)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);

            Teste teste = mapper.treeToValue(root, Teste.class);
            testRepository.save(teste);
        }catch (Exception exception){
            exception.getMessage();
        }
    }*/

    public void salvarDadosOpenFeign(String cep) {
        CepDTO cepDTO = viaCepClient.buscarCep(cep);

        Teste teste = new Teste();
        BeanUtils.copyProperties(cepDTO, teste);
        testRepository.save(teste);
    }
}
