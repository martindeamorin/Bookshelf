package com.example.bookshelf.dao;

import com.example.bookshelf.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDAO extends JpaRepository<Author, Long> {
        public Author findByName(String name);

}
