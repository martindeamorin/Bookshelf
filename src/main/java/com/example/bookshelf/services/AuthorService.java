package com.example.bookshelf.services;

import com.example.bookshelf.dao.AuthorDAO;
import com.example.bookshelf.models.Author;
import com.example.bookshelf.utils.CustomOperations;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService implements CustomOperations<Author> {
    
    @Autowired
    private AuthorDAO authorDAO;
    
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
