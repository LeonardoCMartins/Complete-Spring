package com.retorno.review.pdf.jasper;

import com.retorno.review.configs.apiWebClient.PostDTO;
import com.retorno.review.dtos.AlunoDto;
import com.retorno.review.entities.Aluno;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] exportReport(List<Aluno> alunos) throws Exception {
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/templates/report.jrxml");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(alunos);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Sistema Spring Boot");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

