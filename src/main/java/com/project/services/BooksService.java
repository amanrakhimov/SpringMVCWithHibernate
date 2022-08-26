package com.project.services;

import com.project.entity.Book;
import com.project.entity.ReaderPerson;
import com.project.repositories.BookRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private BookRepositories bookRepositories;

    @Autowired
    public BooksService(BookRepositories bookRepositories){
        this.bookRepositories=bookRepositories;
    }

    public List<Book> findAll(){
        return bookRepositories.findAll();
    }

    public Book findOne(int id){
        Optional<Book> book=bookRepositories.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void save(Book book){
        bookRepositories.save(book);
    }

    @Transactional
    public void update(int id,Book book){
        Book toBeUpdated=bookRepositories.findById(id).get();

        book.setId(id);
        book.setOwner(toBeUpdated.getOwner());

        bookRepositories.save(book);
    }

    @Transactional
    public void delete(int id){
        bookRepositories.deleteById(id);
    }

    public ReaderPerson getOwner(int id){
        return bookRepositories.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void deleteBookFromPerson(int id){
        bookRepositories.findById(id).ifPresent(book -> book.setOwner(null));
    }

    @Transactional
    public void choosePerson(int id,ReaderPerson person){
        bookRepositories.findById(id).ifPresent(book -> book.setOwner(person));
    }
}
