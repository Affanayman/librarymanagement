package com.miniproject.org.mapper;

import com.miniproject.org.dto.AddBookRequest;
import com.miniproject.org.model.Author;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorMapper {

    //all methods are by default static i.e. we don't need to create instance
    public Author mapToAuthor(AddBookRequest addBookRequest){
        return Author.builder().name(addBookRequest.getAuthorName()).email(addBookRequest.getAuthorEmail()).build();
    }
}
