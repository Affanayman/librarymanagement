package com.miniproject.org.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length=30)
    String name;
    @Column(unique = true,nullable = false)
    String email;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties(value = "author")

     List<Book> books;

    @CreationTimestamp
    LocalDate createdDate;
    @UpdateTimestamp
    LocalDate updatedDate;
}
