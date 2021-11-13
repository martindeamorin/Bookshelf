package com.example.bookshelf.services;

import com.example.bookshelf.dao.BookDAO;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.utils.ResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;
    
    @Transactional
    public Book createBook(Book book){
        return this.bookDAO.save(book);
    }
    @Transactional    
    public List<Book> getAllBooks(){
        return this.bookDAO.findAll();
    }
    
    @Transactional
    public void deleteBookById(Long id){
        this.bookDAO.deleteById(id);
    }
    
    @Transactional
    public Book getBookById(Long id){
        return this.bookDAO.getById(id);
    }
    
    public ResponseEntity<Object> editBook(Long id, Book book) throws JsonProcessingException{
       try{
            Book bookToUpdate = this.getBookById(id);
            bookToUpdate.setAuthors(book.getAuthors());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setISBN(book.getISBN());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setEdition(book.getEdition());
            bookToUpdate.setPublished_at(book.getPublished_at());
            Book updatedBook = this.createBook(bookToUpdate);
            return ResponseHandler.generateResponse("Book succesfully updated", HttpStatus.OK, updatedBook);           
       } catch(JsonProcessingException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
       }
    }
    
    public ResponseEntity<Object> deleteBook(Long id)throws JsonProcessingException{
        try{
            this.deleteBookById(id);
            return ResponseHandler.generateResponse("Book with id " + id + " has been sucessfully deleted", HttpStatus.OK, null);
        }catch(JsonProcessingException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> getBooks() throws JsonProcessingException{
        try{
            List<Book> books = this.getAllBooks();
            return ResponseHandler.generateResponse(null, HttpStatus.OK, books);
        } catch(JsonProcessingException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> getBook(Long id) throws JsonProcessingException{
        try{
            Book book = this.getBookById(id);
            return ResponseHandler.generateResponse(null, HttpStatus.OK, book);
        } catch(JsonProcessingException e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
