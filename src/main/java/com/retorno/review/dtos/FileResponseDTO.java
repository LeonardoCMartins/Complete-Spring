package com.retorno.review.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileResponseDTO {
    private String fileName;
    private String downloadUri;
    private String fileType;
    private long size;
}

