package com.example.bookshelf.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class RestBookController {
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public String getBooks(){
        return "hola";
    }
}
