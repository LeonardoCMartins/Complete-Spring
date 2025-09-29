package com.retorno.review.controllers;

import com.retorno.review.configs.exceptions.AlunoException;
import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.entities.Aluno;
import com.retorno.review.pdf.openpdf.AlunoPdfGenerator;
import com.retorno.review.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Tag(name="Alunos", description = "Operações relacionadas aos alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @Autowired
    private AlunoPdfGenerator pdfGenerator;

    //QUANDO DA ERRO AO SALVAR UM ALUNO E QND FOR CADASTRAR O PROXIMO O ID FICA 3 AO INVES DE 2 POR EXEMPLO - AJEITAR ISSO
    @Operation(summary = "Cadastra um novo aluno",
              description = "Cria um novo aluno no sistema a partir dos dados fornecidos no corpo da requisição")
    @PostMapping("/aluno/post")
    public ResponseEntity<AlunoDto> save (@RequestBody AlunoDto alunoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAluno(alunoDto));
    }

    @GetMapping("/alunos")
    public ResponseEntity<List<AlunoDto>> getAllPage(@RequestParam int pagina, @RequestParam int itens){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPage(pagina, itens));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida Retorna uma lista com todos os alunos"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @GetMapping("/alunoslist")
    public ResponseEntity<List<AlunoDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/alunos/{id}")
    public ResponseEntity<AlunoDto> getById(@Parameter(description = "ID do aluno", example = "1") @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PutMapping("/aluno/{id}")
    public ResponseEntity updat(@PathVariable Long id, @RequestBody AlunoDto alunoDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAluno(id, alunoDto));
    }

    @DeleteMapping("/aluno/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado");
    }

    @GetMapping("/qntd")
    public ResponseEntity<Integer> qntdAlunos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.qntdAlunos());
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGenerator.export(response);
    }

    @GetMapping("/query/aluno")
    public ResponseEntity<Optional<AlunoDto>> findByEmail(@RequestParam String email) {
        return ResponseEntity.status(HttpStatus.OK).body(service.alunoFindEmail(email));
    }

    @ExceptionHandler(AlunoException.class)
    public ResponseEntity exception(AlunoException alunoException){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(alunoException.getMessage());
    }


}

