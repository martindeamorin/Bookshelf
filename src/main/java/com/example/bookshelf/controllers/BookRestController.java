package com.example.bookshelf.controllers;

import com.example.bookshelf.models.Book;
import com.example.bookshelf.services.BookService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public List<Book> getAllBooks(){
        return this.bookService.getAllBooks();
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return this.bookService.createBook(book);
    }
}
