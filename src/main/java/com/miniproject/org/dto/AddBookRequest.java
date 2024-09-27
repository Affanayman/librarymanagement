package com.miniproject.org.dto;

import com.miniproject.org.enums.BookType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequest {
    @NotBlank(message = "book title should not e null")
    String bookTitle;
    @NotBlank(message = "book no should not be blank")
    String bookNo;
    @Positive(message = "should not be negative")
    int securityAmount;
    @NotNull(message = "book type should not be null")
    BookType bookType;
    @NotBlank(message = "name should not be blank")
    String authorName;
    @NotBlank(message = "mail should not be blank")
    @Email
    String authorEmail;
}
