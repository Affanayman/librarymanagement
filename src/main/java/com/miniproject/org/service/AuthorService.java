package com.miniproject.org.service;

import com.miniproject.org.model.Author;
import com.miniproject.org.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public Author getAuthorByEmail(String email) {
        return authorRepository.findAuthorByEmailByNativeQuery(email);

    }
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
