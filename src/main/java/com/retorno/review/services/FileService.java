package com.retorno.review.services;

import com.retorno.review.configs.exceptions.FileStorageException;
import com.retorno.review.configs.exceptions.MyFileNotFoundException;
import com.retorno.review.dtos.FileResponseDTO;
import com.retorno.review.entities.FileEntity;
import com.retorno.review.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    @Value("${file.upload-dir}")
    private Path fileStorageLocation;

    private final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB

    public FileResponseDTO saveFile(MultipartFile file) throws IOException {
        // Validação
        if (file.isEmpty()) throw new FileStorageException("Arquivo vazio");
        if (!file.getContentType().startsWith("image/"))
            throw new FileStorageException("Apenas imagens são permitidas");
        if (file.getSize() > MAX_FILE_SIZE)
            throw new FileStorageException("Arquivo muito grande, máximo 5MB");

        // Gera UUID para evitar conflito
        String storedFileName = java.util.UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetLocation = fileStorageLocation.resolve(storedFileName);

        // Cria diretório se não existir
        Files.createDirectories(fileStorageLocation);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        FileEntity entity = FileEntity.builder()
                .fileName(file.getOriginalFilename())
                .storedFileName(storedFileName)
                .filePath(targetLocation.toString())
                .fileType(file.getContentType())
                .size(file.getSize())
                .downloadUri("/api/files/download/" + storedFileName)
                .build();

        repository.save(entity);

        return new FileResponseDTO(
                entity.getFileName(),
                entity.getDownloadUri(),
                entity.getFileType(),
                entity.getSize()
        );
    }

    public Resource loadFile(String storedFileName) {
        try {
            Path filePath = fileStorageLocation.resolve(storedFileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) return resource;
            else throw new MyFileNotFoundException("Arquivo não encontrado: " + storedFileName);

        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("Arquivo não encontrado: " + storedFileName);
        }
    }

    public List<FileResponseDTO> listFiles() {
        return repository.findAll().stream().map(entity ->
                new FileResponseDTO(
                        entity.getFileName(),
                        entity.getDownloadUri(),
                        entity.getFileType(),
                        entity.getSize()
                )
        ).toList();
    }
}

