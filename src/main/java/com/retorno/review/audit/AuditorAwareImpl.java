package com.retorno.review.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Pegando o usuário autenticado com o SpringSecurity:
        // var auth = SecurityContextHolder.getContext().getAuthetication();
        // return Optional.of(auth.getName());
        return Optional.of("leo");
    }
}
