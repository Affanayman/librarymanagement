package com.miniproject.org.service;

import com.miniproject.org.dto.AddUserRequest;
import com.miniproject.org.enums.UserType;
import com.miniproject.org.mapper.UserMapper;
import com.miniproject.org.model.User;
import com.miniproject.org.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User addStudent(AddUserRequest addUserRequest) {
        User userSaved= UserMapper.mapToUser(addUserRequest);
        userSaved.setUserType(UserType.STUDENT);
        return userRepository.save(userSaved);
    }
    public User addAdmin(AddUserRequest addUserRequest) {
        User userSaved= UserMapper.mapToUser(addUserRequest);
        userSaved.setUserType(UserType.ADMIN);
        return userRepository.save(userSaved);
    }
    public User fetchUserByMail(String email,UserType userType){
        return userRepository.findByEmailAndUserType(email,userType);
    }
}
