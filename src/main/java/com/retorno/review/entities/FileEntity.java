package com.retorno.review.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;      // Nome original
    private String storedFileName; // Nome armazenado (UUID)
    private String filePath;       // Caminho no sistema
    private String downloadUri;    // URL amigável para download
    private String fileType;
    private long size;
}
