package com.retorno.review.configs.apiWebClient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {
    private Integer userId;
    private String title;
    private String body;
}

