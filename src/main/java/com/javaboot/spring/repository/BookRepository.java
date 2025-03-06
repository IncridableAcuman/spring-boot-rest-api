package com.javaboot.spring.repository;

import com.javaboot.spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
