package com.miniproject.org.model;

import com.miniproject.org.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false,length =30,unique = true)
    String transactionId;
    @ManyToOne
    Book book;
    @ManyToOne
    User user;
    int settlementAmount;
    @Enumerated
    TransactionStatus transactionStatus;
    @CreationTimestamp(source = SourceType.DB)
    LocalDate createdDate;
    @UpdateTimestamp
    LocalDate updatedDate;
}
