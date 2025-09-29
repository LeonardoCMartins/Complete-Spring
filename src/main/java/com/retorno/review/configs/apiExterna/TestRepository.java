package com.retorno.review.configs.apiExterna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Teste, Long> {
    void deleteByCep(String cep);
}
