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
    
    @Autowired
    private AuthorService authorService;
    
    @Transactional
    public Book saveBook(Book book){
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
    
    public ResponseEntity<Object> createBook(Book book){
        try{
            Book newBook = Book.builder()
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .description(book.getDescription())
                .edition(book.getEdition())
                .collection(book.getCollection())
                .authors(authorService.findOrCreate(book.getAuthors()))
                .published_at(book.getPublished_at())
                .publisher(book.getPublisher())
                .genres(book.getGenres()).build();
            Book savedBook = this.saveBook(newBook);  
            return ResponseHandler.generateResponse("Libre creado con exito", HttpStatus.CREATED, savedBook);
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> editBook(Long id, Book book){
       try{
            Book bookToUpdate = this.getBookById(id);
            bookToUpdate.setAuthors(book.getAuthors());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setISBN(book.getISBN());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setEdition(book.getEdition());
            bookToUpdate.setPublished_at(book.getPublished_at());
            Book updatedBook = this.saveBook(bookToUpdate);
            return ResponseHandler.generateResponse("Book succesfully updated", HttpStatus.OK, updatedBook);           
       } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
       }
    }
    
    public ResponseEntity<Object> deleteBook(Long id){
        try{
            this.deleteBookById(id);
            return ResponseHandler.generateResponse("Book with id " + id + " has been sucessfully deleted", HttpStatus.OK, null);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> getBooks() {
        try{
            List<Book> books = this.getAllBooks();
            return ResponseHandler.generateResponse(null, HttpStatus.OK, books);
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> getBook(Long id)  {
        try{
            Book book = this.getBookById(id);
            return ResponseHandler.generateResponse(null, HttpStatus.OK, book);
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
