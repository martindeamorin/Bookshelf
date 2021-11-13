package com.example.bookshelf.controllers;

import com.example.bookshelf.models.Account;
import com.example.bookshelf.services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserRestController {
    
    @Autowired
    AccountService accountService;
    
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Account user) throws JsonProcessingException{
        return this.accountService.register(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Account user) throws JsonProcessingException{
        return this.accountService.login(user);
    }
    

}
