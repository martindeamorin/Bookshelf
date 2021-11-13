package com.example.bookshelf.services;

import com.example.bookshelf.dao.BookDAO;
import com.example.bookshelf.models.Book;
import com.example.bookshelf.utils.ResponseHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService implements ICommonService<Book>{
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired
    private AuthorService authorService;
    
    @Override
    public Book save(Book entity) {
        return this.bookDAO.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.bookDAO.deleteById(id);
    }

    @Override
    public List<Book> findOrCreate(List<Book> entityList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.findAll();
    }

    @Override
    public Book getById(Long id) {
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
            Book savedBook = this.save(newBook);  
            return ResponseHandler.generateResponse("Libre creado con exito", HttpStatus.CREATED, savedBook);
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> editBook(Long id, Book book){
       try{
            Book bookToUpdate = this.getById(id);
            bookToUpdate.setAuthors(book.getAuthors());
            bookToUpdate.setDescription(book.getDescription());
            bookToUpdate.setISBN(book.getISBN());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setEdition(book.getEdition());
            bookToUpdate.setPublished_at(book.getPublished_at());
            Book updatedBook = this.save(bookToUpdate);
            return ResponseHandler.generateResponse("Book succesfully updated", HttpStatus.OK, updatedBook);           
       } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
       }
    }
    
    public ResponseEntity<Object> deleteBook(Long id){
        try{
            this.deleteById(id);
            return ResponseHandler.generateResponse("Book with id " + id + " has been sucessfully deleted", HttpStatus.OK, null);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> getBooks() {
        try{
            List<Book> books = this.getAll();
            return ResponseHandler.generateResponse(null, HttpStatus.OK, books);
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    public ResponseEntity<Object> getBook(Long id)  {
        try{
            Book book = this.getById(id);
            return ResponseHandler.generateResponse(null, HttpStatus.OK, book);
        } catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
