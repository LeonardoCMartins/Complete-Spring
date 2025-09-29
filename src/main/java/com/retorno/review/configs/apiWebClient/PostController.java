package com.retorno.review.configs.apiWebClient;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/sync-posts")
    public Flux<PostDTO> syncPosts() {
        return postService.fetchAndSavePosts();
    }

    @GetMapping("/test-all")
    public Flux<PostDTO> findAll() {
        return postService.findAll();
    }

    @GetMapping("/test-one/{id}")
    public Flux<PostDTO> findOne(@PathVariable Long id) {
        return postService.findOne(id);
    }

    @PostMapping("/create")
    public Mono<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @PostMapping("/save")
    public Mono<PostDTO> createPostAndSave(@RequestBody PostDTO postDTO) {
        return postService.createPostAndSave(postDTO);
    }
}
