package com.miniproject.org.controller;

import com.miniproject.org.dto.AddUserRequest;
import com.miniproject.org.mapper.UserMapper;
import com.miniproject.org.model.User;
import com.miniproject.org.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/student")
    public ResponseEntity<User> addStudent(@RequestBody  @Valid AddUserRequest addUserRequest){

        User student=userService.addStudent(addUserRequest);
        return new ResponseEntity<>(student, HttpStatus.CREATED);


    }
    @PostMapping("/admin")
    public ResponseEntity<User> addAdmin(@RequestBody @Valid  AddUserRequest addUserRequest){

        User admin=userService.addAdmin(addUserRequest);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);


    }
}
