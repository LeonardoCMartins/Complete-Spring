package com.retorno.review.aws.s3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        s3Service.uploadFile(file.getOriginalFilename(), file.getInputStream(), file.getSize());
        return ResponseEntity.ok("File uploaded: " + s3Service.getFileUrl(file.getOriginalFilename()));
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<String> getFileUrl(@PathVariable String fileName) {
        String url = s3Service.generatePresignedUrl(fileName);
        return ResponseEntity.ok(url);
        // SE QUISER QUE O LINK DAS URLS MOSTREM AS IMAGENS TEM QUE DEIXAR O BUCKET "ABERTO" COM AQLS PERMISSÕES
    }

    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        s3Service.deleteFile(fileName);
        return ResponseEntity.ok("File deleted: " + fileName);
    }
}
