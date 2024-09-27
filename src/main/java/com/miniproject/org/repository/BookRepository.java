package com.miniproject.org.repository;

import com.miniproject.org.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findBookByBookNo(String bookNo);
}
