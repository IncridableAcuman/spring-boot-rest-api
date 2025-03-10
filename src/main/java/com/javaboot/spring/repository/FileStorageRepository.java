package com.javaboot.spring.repository;

import com.javaboot.spring.model.FileStorage;
import com.javaboot.spring.model.FileStorageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {
     FileStorage findByHashId(String hashId);

     List<FileStorage> findAllByFileStorageStatus(FileStorageStatus status);
}
