package com.javaboot.spring.service;

import com.javaboot.spring.model.Book;
import com.javaboot.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    public List<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }
    public List<Book> findByRating(Integer rating){
        return bookRepository.findByRating(rating);
    }
    public List<Book> findByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }
    @Scheduled(cron = "0 53 11 * * * ")
    public Book saveCron(){
        Book book = new Book();
        book.setTitle("Xamsa");
        book.setAuthor("Alisher Navoiy");
        book.setContent("Ushbu Xamsa 5 lik daston hisoblanadi");
        book.setGenre("doston");
        book.setRating(7);
        return bookRepository.save(book);
    }

}
