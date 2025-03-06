package com.javaboot.spring.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column
    private String content;
    @Column
    private String genre;
    @Column
    private Integer rating;


    public Long getId(){ return id;}
    public void setId(Long id){ this.id=id; }

    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title=title; }

    public String getAuthor(){ return  author; }
    public void setAuthor(String author){ this.author=author; }

    public String getContent(){ return content; }
    public void setContent(String content){ this.content=content; }

    public String getGenre(){ return genre; }
    public void setGenre(String genre){ this.genre=genre; }

    public Integer getRating(){ return rating; }
    public void setRating(Integer rating){ this.rating=rating; }

}
