package com.retorno.review.configs.apiWebClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

    public Flux<PostDTO> fetchAndSavePosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostDTO.class)
                .map(dto -> {
                    // Map DTO para Entity
                    PostEntity entity = new PostEntity();
                    entity.setUserId(dto.getUserId());
                    entity.setTitle(dto.getTitle());
                    entity.setBody(dto.getBody());

                    // Salva no banco (bloqueante)
                    postRepository.save(entity);

                    return dto; // retorna DTO
                });
    }

    public Flux<PostDTO> findAll(){
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostDTO.class);
    }

    public Flux<PostDTO> findOne(Long id){
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToFlux(PostDTO.class)
                .onErrorResume( e -> Mono.empty());
    }

    public Mono<PostDTO> createPost(PostDTO postDTO) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(postDTO)  // envia o DTO como corpo da requisição
                .retrieve()
                .bodyToMono(PostDTO.class) // espera receber o objeto criado de volta
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.empty(); // caso dê erro, retorna vazio
                });
    }

    // Um post via API externa e também salvar esse post no seu banco MySQL
    public Mono<PostDTO> createPostAndSave(PostDTO postDTO) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(postDTO) // envia o DTO
                .retrieve()
                .bodyToMono(PostDTO.class) // espera um PostDTO de volta
                .map(savedFromApi -> {
                    // Converter DTO -> Entity
                    PostEntity entity = new PostEntity();
                    entity.setUserId(savedFromApi.getUserId());
                    entity.setTitle(savedFromApi.getTitle());
                    entity.setBody(savedFromApi.getBody());

                    // Salvar no banco
                    postRepository.save(entity);

                    return savedFromApi; // retorna o que veio da API
                });
    }
}