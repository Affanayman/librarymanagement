package com.miniproject.org.model;

import com.miniproject.org.enums.UserStatus;
import com.miniproject.org.enums.UserType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    @Column(length=30)
    String name;
    @Column(unique=true,nullable=false,length=50)
    String email;
    @Column(unique=true,length=15)
    String phoneNo;
    String address;
    @OneToMany(mappedBy = "user")
    List<Transaction> transactions;
    @OneToMany(mappedBy="user")
    List<Book> books;
    @Enumerated(EnumType.STRING)
    UserType userType;
    @Enumerated
    UserStatus userStatus;
    @CreationTimestamp(source = SourceType.DB)
    LocalDate createdDate;
    @UpdateTimestamp
    LocalDate updatedDate;

}
