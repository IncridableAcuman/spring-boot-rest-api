package com.javaboot.spring.controller;

import com.javaboot.spring.model.Book;
import com.javaboot.spring.service.BookService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }
    @PostMapping("/book")
    public ResponseEntity<Book> saveBook( @RequestBody Book book){
        try {
            Book book1 = bookService.save(book);
            return ResponseEntity.ok(book1);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/book/all")
    public ResponseEntity findAll(){
        try {
            List<Book> book = bookService.findAll();
            return ResponseEntity.ok(book);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/book/isExist/{id}")
    public ResponseEntity existBook(@PathVariable Long id){
        try {
          Boolean book = bookService.existBook(id);
          return  ResponseEntity.ok(book);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/book/{id}")
    public ResponseEntity findBookById(@PathVariable Long id){
        try {
            Optional<Book> book = bookService.findBookById(id);
            return ResponseEntity.ok(book);
        } catch (IllegalArgumentException e){
         return ResponseEntity.badRequest().body(null);
        }
    }
    @DeleteMapping("/book/{id}")
    public ResponseEntity deleteBookById(@PathVariable Long id){
        try {
            bookService.deleteBookById(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/book/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody Book book){
        try {
         Book book1 = bookService.updateBook(id,book);
         return ResponseEntity.ok(book1);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @GetMapping("/book/ratings/{rating}")
    public ResponseEntity findByRating(@PathVariable Integer rating){
        List<Book> books = bookService.findByRating(rating);
        return  ResponseEntity.ok(books);
    }
    @GetMapping("/book/titles{title}")
    public ResponseEntity findByTitle(@PathVariable String title ){
        List<Book> books = bookService.findByTitle(title);
        return  ResponseEntity.ok(books);
    }
    @GetMapping("/book/genres/{genre}")
    public ResponseEntity findByGenre(@PathVariable String genre ){
        List<Book> books = bookService.findByGenre(genre);
        return  ResponseEntity.ok(books);
    }
}
