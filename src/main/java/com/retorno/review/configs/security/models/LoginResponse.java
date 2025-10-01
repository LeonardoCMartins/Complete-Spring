package com.retorno.review.configs.security.models;

public record LoginResponse(String accessToken, Long expiresIn) {
}
