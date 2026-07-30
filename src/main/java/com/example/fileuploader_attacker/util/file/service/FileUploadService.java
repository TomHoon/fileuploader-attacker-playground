package com.example.fileuploader_attacker.util.file.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucket;

    public String upload(MultipartFile file) throws Exception {

//        String fileName =
//                UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(fileName)
                        .stream(
                                file.getInputStream(),
                                file.getSize(),
                                -1
                        )
                        .contentType(file.getContentType())
                        .build()
        );

        return fileName;
    }
}