package com.retorno.review;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
API Simples

 Autenticação e Autorização - JWT e Spring Security (keyclock, oauth2.0)

 Validação de Dados - @Valid, @NotNull, @Email, @Password ✔
 Relacionamentos entre entidades - @OneToMany, @ManyToOne, @ManyToMany ✔

 Dashboard com dados agregados - Endpoints para estatísticas: Total de usuários, Produtos mais vendidos, Faturamento mensal

  * Use JPQL ou Criteria API para fazer queries mais complexas (acho q isso é bom para dashboards) ✔
 Upload de arquivos (imagem, PDF, etc.) ✔
 Criação de Relatórios em PDF ✔
 Envio de e-mails ✔
 Consumo de APIs externas ✔
 Criar um front-end ou app que consome sua API
 TESTES ✔
 Redis ✔
 Angular
 AWS - S3 ✔    SQS (Simple Queue Service), CloudWatch(p/ logs), IAM + Secrets Manager(segurança) e Deploy EC2


 Ideia completa de aplicação: Plataforma de Cursos
 	- Cadastro de alunos e professores
 	- Cursos, aulas e materiais
 	- Sistema de login e permissão (JWT)
 	- Upload de arquivos (PDF, vídeo)
 	- Painel de progresso dos alunos
 	- Dashboard com estatísticas
 	- Emails automáticos

  * Spring Boot + Spring Data JPA, Spring Security + JWT, Bean Validation, SQL, MultipartFile + armazenamento local ou S3,
    Spring Email + SMTP, FRONTEND, Swagger (OpenAPI), Testes (Junit + Mockito)
 */

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API DE REVISÃO", version = "10.0", description = "API para estudar conceitos do Spring"))
public class ReviewApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}
}
