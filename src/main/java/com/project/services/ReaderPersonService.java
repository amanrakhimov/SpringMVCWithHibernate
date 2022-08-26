package com.project.services;

import com.project.entity.Book;
import com.project.entity.ReaderPerson;
import com.project.repositories.ReaderPersonRepositories;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReaderPersonService {

    private ReaderPersonRepositories readerPersonRepositories;

    @Autowired
    public ReaderPersonService(ReaderPersonRepositories readerPersonRepositories){
        this.readerPersonRepositories=readerPersonRepositories;
    }

    public List<ReaderPerson> findAll(){
        return readerPersonRepositories.findAll();
    }

    public ReaderPerson findOne(int id){
        return readerPersonRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(ReaderPerson person){
        readerPersonRepositories.save(person);
    }

    @Transactional
    public void update(int id,ReaderPerson person){
        person.setId(id);
        readerPersonRepositories.save(person);
    }

    @Transactional
    public void delete(int id){
        readerPersonRepositories.deleteById(id);
    }

    public List<Book> getBooks(int id){
        Optional<ReaderPerson> person = readerPersonRepositories.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBookList());
            return person.get().getBookList();
        }
        else {
            return Collections.emptyList();
        }
    }
}
