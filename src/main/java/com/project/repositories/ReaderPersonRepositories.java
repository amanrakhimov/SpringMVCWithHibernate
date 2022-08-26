package com.project.repositories;

import com.project.entity.ReaderPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderPersonRepositories extends JpaRepository<ReaderPerson,Integer> {
}
