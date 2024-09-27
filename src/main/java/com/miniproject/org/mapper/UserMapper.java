package com.miniproject.org.mapper;

import com.miniproject.org.dto.AddUserRequest;
import com.miniproject.org.enums.UserStatus;
import com.miniproject.org.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass

public class UserMapper {
    public User mapToUser(AddUserRequest addUserRequest){
        return User.builder().name(addUserRequest.getName()).email(addUserRequest.getEmail()).address(addUserRequest.getAddress()).phoneNo(addUserRequest.getPhoneNo()).userStatus(UserStatus.ACTIVE).build();
    }
}
