package com.miniproject.org.dto;

import com.miniproject.org.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddUserRequest {
    String name;
    @NotBlank(message = "required mail")
    String email;

    String phoneNo;

    String address;


}
