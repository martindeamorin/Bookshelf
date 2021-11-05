package com.example.bookshelf.services;

import com.example.bookshelf.dao.BookDAO;
import com.example.bookshelf.models.Book;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;
    
    public Book createBook(Book book){
        return this.bookDAO.save(book);
    }
    
    public List<Book> getAllBooks(){
        return this.bookDAO.findAll();
    }
}
