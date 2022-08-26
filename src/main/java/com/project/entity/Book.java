package com.project.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Enter name of book")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Enter author of book")
    @Column(name = "author")
    private String author;

    @Max(2022)
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "personid",referencedColumnName = "id")
    private ReaderPerson owner;


    public Book(){

    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public ReaderPerson getOwner() {
        return owner;
    }

    public void setOwner(ReaderPerson owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
