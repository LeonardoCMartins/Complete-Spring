package com.retorno.review.controllers;

import com.retorno.review.dtos.FileResponseDTO;
import com.retorno.review.entities.FileEntity;
import com.retorno.review.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api-leo/files")
public class FileController {

    @Autowired
    private FileService service;

    @PostMapping("/upload")
    public ResponseEntity<FileResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.saveFile(file));
    }

    @GetMapping("/download/{storedFileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String storedFileName) {
        Resource resource = service.loadFile(storedFileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/list")
    public ResponseEntity<List<FileResponseDTO>> listFiles() {
        return ResponseEntity.ok(service.listFiles());
    }
}

