package com.miniproject.org.service;

import com.miniproject.org.dto.AddBookRequest;
import com.miniproject.org.mapper.AuthorMapper;
import com.miniproject.org.mapper.BookMapper;
import com.miniproject.org.model.Author;
import com.miniproject.org.model.Book;
import com.miniproject.org.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    AuthorService authorService;
    @Autowired
    BookRepository bookRepository;
    public Book addBook(AddBookRequest addBookRequest){
        Author authorFromDb=authorService.getAuthorByEmail(addBookRequest.getAuthorEmail());
        if(authorFromDb==null){
            authorFromDb= AuthorMapper.mapToAuthor(addBookRequest);
            authorFromDb=authorService.saveAuthor(authorFromDb);
        }
        Book book = BookMapper.mapToBook(addBookRequest);
        book.setAuthor(authorFromDb);
       return bookRepository.save(book);
    }

    public Book getBookByBookNo(String bookNo) {
        return bookRepository.findBookByBookNo(bookNo);
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }

}
