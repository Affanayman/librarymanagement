package com.miniproject.org.model;

import com.miniproject.org.enums.BookType;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true,nullable = false,length = 10)
    String  bookNo;
    @Enumerated(value=EnumType.STRING)
    BookType bookType;
    @Column(length=30)
    String bookTitle;
    int securityAmount;
    @OneToMany(mappedBy = "book")
    List<Transaction> transactions;
    @ManyToOne

    User user;
    @ManyToOne
    @JoinColumn
    Author author;
    @CreationTimestamp(source = SourceType.DB)
    LocalDate createdDate;
    @UpdateTimestamp
    LocalDate updatedDate;
}
