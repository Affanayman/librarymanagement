package com.miniproject.org.repository;

import com.miniproject.org.enums.UserType;
import com.miniproject.org.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
    User findByEmailAndUserType(String email, UserType userType);
}
