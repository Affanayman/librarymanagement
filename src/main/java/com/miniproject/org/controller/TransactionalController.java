package com.miniproject.org.controller;

import com.miniproject.org.dto.TransactionRequest;
import com.miniproject.org.model.Transaction;
import com.miniproject.org.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionalController {
    @Autowired
    TransactionService transactionService;
    @PutMapping("/issue")
    public ResponseEntity<Transaction> issueBook(@RequestBody TransactionRequest transactionRequest){
        Transaction  createdTransaction = transactionService.issueBook(transactionRequest);
        return new ResponseEntity<>(createdTransaction, HttpStatus.OK);
    }

    @PutMapping("/return")
    public ResponseEntity<Integer> returnBook(@RequestBody TransactionRequest transactionRequest){
        Integer  settlementAmount = transactionService.returnBook(transactionRequest);
        return new ResponseEntity<>(settlementAmount, HttpStatus.OK);
    }

}
