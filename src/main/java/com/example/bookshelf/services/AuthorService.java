package com.example.bookshelf.services;

import com.example.bookshelf.dao.AuthorDAO;
import com.example.bookshelf.models.Author;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements ICommonService<Author> {
    
    @Autowired
    private AuthorDAO authorDAO;
    
    @Override
    public Author save(Author entity) {
        return this.authorDAO.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.authorDAO.deleteById(id);
    }

    @Override
    public List<Author> getAll() {
        return this.authorDAO.findAll();
    }

    @Override
    public Author getById(Long id) {
        return this.authorDAO.getById(id);
    }
    
    public Author getAuthorByName(String name){
        return authorDAO.findByName(name);
    }
    
    public Author saveAuthor(Author author){
        return authorDAO.save(author);
    }

    @Override
    public List<Author> findOrCreate(List<Author> authorList) {
        ArrayList<Author> authors = new ArrayList();
        authorList.forEach(a -> {
            Author findAuthor = this.getAuthorByName(a.getName());
            if(findAuthor == null){
                authors.add(this.saveAuthor(a));
            } else{
                authors.add(findAuthor);
            }
        });
        return authors;
    }
    
}
