package com.example.fileuploader_attacker.util.file.controller;
//
//import com.example.fileuploader_attacker.util.file.service.FileUploadService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/files")
//public class FileUploadController {
//
//    private final FileUploadService fileUploadService;
//
//    @PostMapping("/upload")
//    public String upload(@RequestParam MultipartFile file) throws Exception {
//
//        return fileUploadService.upload(file);
//    }
//}

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileUploadController {

	private static final String UPLOAD_DIR = "./uploads/";
    private final Path uploadPath = Paths.get("./uploads");


    @GetMapping("/files/{fileName}/download")
    public ResponseEntity<Resource> download(
            @PathVariable String fileName
    ) throws IOException {

        Path filePath = uploadPath.resolve(fileName).normalize();

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception {
		try {
			if (file.isEmpty()) {
				return ResponseEntity.badRequest().body("파일이 없습니다.");
			}

			String contentType = file.getContentType();

			if (contentType == null ||
							!(contentType.equals("application/pdf")
											|| contentType.startsWith("image/"))) {

				return ResponseEntity.badRequest()
								.body("PDF 또는 이미지만 업로드 가능합니다.");
			}

			Path uploadPath = Paths.get(UPLOAD_DIR);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			String originalFilename = file.getOriginalFilename();

			String extension = "";

			if (originalFilename != null && originalFilename.contains(".")) {
				extension = originalFilename.substring(
								originalFilename.lastIndexOf(".")
				);
			}

			String savedFilename =
							UUID.randomUUID() + extension;

			Path filePath =
							uploadPath.resolve(savedFilename);

			Files.copy(
							file.getInputStream(),
							filePath,
							StandardCopyOption.REPLACE_EXISTING
			);

			return ResponseEntity.ok(
							Map.of(
											"fileName", savedFilename,
											"originalFilename", originalFilename
							)
			);

		} catch (IOException e) {
			return ResponseEntity.internalServerError()
							.body("파일 저장 실패");
		}
	}
}