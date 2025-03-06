package com.javaboot.spring.service;

import com.javaboot.spring.model.Book;
import com.javaboot.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }
    public Book save(Book book){
        return bookRepository.save(book);
    }
    public List<Book> findAll(){
        return this.bookRepository.findAll();
    }
    public Optional<Book> findBookById(Long id){
        return this.bookRepository.findById(id);
    }
    public Boolean existBook(Long id){
        return this.bookRepository.findById(id).isPresent();
    }
    public void deleteBookById(Long id){
         this.bookRepository.deleteById(id);
    }
    public Book updateBook(Long id,Book book){
        Optional<Book> book1 = bookRepository.findById(id);
        if (book1.isEmpty()){
            throw new IllegalArgumentException("Book with ID " + "not found");
        }
        Book book2 = book1.get();
        book2.setTitle(book.getTitle());
        book2.setAuthor(book.getAuthor());
        book2.setContent(book.getContent());
        book2.setGenre(book.getGenre());
        book2.setRating(book.getRating());
        return bookRepository.save(book2);
    }
}
