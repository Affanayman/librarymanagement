package com.miniproject.org.controller;

import com.miniproject.org.model.Author;
import com.miniproject.org.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @GetMapping()
    public ResponseEntity<Author> getAuthor(@RequestParam String email) {
        Author authorDetails =authorService.getAuthorByEmail(email);
        return new ResponseEntity<>(authorDetails, HttpStatus.OK);
    }
}
