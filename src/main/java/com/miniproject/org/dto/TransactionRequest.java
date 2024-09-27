package com.miniproject.org.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {
    @NotBlank(message="book no is mandatory")
    String bookNo;
    @NotBlank(message = "mail is mandatory")
    String userEmail;
}
