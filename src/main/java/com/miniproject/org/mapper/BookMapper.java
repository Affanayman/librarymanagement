package com.miniproject.org.mapper;

import com.miniproject.org.dto.AddBookRequest;
import com.miniproject.org.model.Book;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookMapper {
    public Book mapToBook(AddBookRequest addBookRequest){
        return Book.builder().bookTitle(addBookRequest.getBookTitle()).bookNo(addBookRequest.getBookNo()).bookType(addBookRequest.getBookType()).securityAmount(addBookRequest.getSecurityAmount()).build();   }

}
