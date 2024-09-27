package com.miniproject.org.repository;

import com.miniproject.org.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value="select * from Author where email =:email",nativeQuery = true)
    Author findAuthorByEmailByNativeQuery(String email);
}
