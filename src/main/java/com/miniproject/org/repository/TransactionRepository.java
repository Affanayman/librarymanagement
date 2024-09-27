package com.miniproject.org.repository;

import com.miniproject.org.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByUserEmailAndBookNo(String userEmail, String bookNo);
}
