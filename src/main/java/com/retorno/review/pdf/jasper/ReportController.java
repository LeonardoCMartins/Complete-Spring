package com.retorno.review.pdf.jasper;

import com.retorno.review.configs.apiWebClient.PostDTO;
import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.entities.Aluno;
import com.retorno.review.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ReportController {

    private final ReportService reportService;
    private final AlunoService alunoService;

    public ReportController(ReportService reportService, AlunoService alunoService) {
        this.reportService = reportService;
        this.alunoService = alunoService;
    }

    @GetMapping(value = "/alunos-report", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateAlunosReport() throws Exception {
        List<Aluno> alunos = alunoService.reportAllAlunos();

        byte[] pdf = reportService.exportReport(alunos);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=alunos_report.pdf")
                .body(pdf);
    }
}

