package com.example.bookshelf.controllers;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookRestController {
    
    @Autowired
    private BookService bookService;
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<Object> getBooks() throws JsonProcessingException{
        return this.bookService.getBooks();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return this.bookService.createBook(book);
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> editBook(@RequestBody Book book, @PathVariable("id") Long id) throws JsonProcessingException{
        return this.bookService.editBook(id, book);
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") Long id) throws JsonProcessingException{
        return this.bookService.deleteBook(id);
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getBook(@PathVariable("id") Long id) throws JsonProcessingException{
        return this.bookService.getBook(id);
    }
}
