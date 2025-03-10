package com.javaboot.spring.controller;

import com.javaboot.spring.model.FileStorage;
import com.javaboot.spring.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/api")
public class FileStorageController {
    private final FileStorageService fileStorageService;
    public FileStorageController(FileStorageService fileStorageService){
        this.fileStorageService=fileStorageService;
    }
    @Value("${upload.folder}")
    private String uploadFolder;
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile multipartFile){
        fileStorageService.save(multipartFile);
        return  ResponseEntity.ok(multipartFile.getOriginalFilename() + "file saved");
    }
    @GetMapping("/file/all")
    public ResponseEntity getAll(FileStorage fileStorage){
        List<FileStorage> fileStorages=fileStorageService.getAllFiles();
        return  ResponseEntity.ok(fileStorages);
    }
    @GetMapping("/preview/{hashId}")
    public ResponseEntity download(@PathVariable String hashId) throws IOException {
        FileStorage fileStorage=fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"inline; fileName=\"" + URLEncoder.encode(fileStorage.getHashId()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,fileStorage.getUploadPath())));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity findByHashId(@PathVariable String hashId) throws IOException {
        FileStorage fileStorage=fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\"" + URLEncoder.encode(fileStorage.getHashId()))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,fileStorage.getUploadPath())));
    }
    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity delete(@PathVariable String hashId){
        fileStorageService.delete(hashId);
        return ResponseEntity.ok("File deleted");
    }
}
