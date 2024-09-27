package com.miniproject.org.service;

import com.miniproject.org.dto.TransactionRequest;
import com.miniproject.org.enums.TransactionStatus;
import com.miniproject.org.enums.UserStatus;
import com.miniproject.org.enums.UserType;
import com.miniproject.org.exception.TransactionException;

import com.miniproject.org.model.Book;
import com.miniproject.org.model.Transaction;
import com.miniproject.org.model.User;
import com.miniproject.org.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.ZoneId;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    TransactionRepository transactionRepository;
    @Value("${book.maximum.validity}")
    int validDays;
    @Value("${book.fine.per.day}")
    int finePerDay;
    public Transaction issueBook(TransactionRequest transactionRequest) {
        User user = fetchUser(transactionRequest);
        if (user.getUserStatus() == UserStatus.BLOCKED) {
            throw new TransactionException("user is blocked");
        }
        Book book = fetchBook(transactionRequest);
        if (book.getUser() != null) {
            throw new TransactionException("Book already issued by another user");
        }
        return issueBook(book, user);

    }
    @Transactional
    private Transaction issueBook(Book book, User user) {
        Transaction transaction = Transaction.builder().book(book).user(user).transactionStatus(TransactionStatus.Issued).transactionId(UUID.randomUUID().toString().substring(0,30)).settlementAmount(-book.getSecurityAmount()).build();
        transactionRepository.save(transaction);

        //update book user

        book.setUser(user);
        bookService.updateBook(book);
        return transaction;

    }

        private User fetchUser(TransactionRequest request){

            User user = userService.fetchUserByMail(request.getUserEmail(), UserType.STUDENT);
            if (user == null) {
                throw new TransactionException("User not found");
            }
            if (user.getUserType() != UserType.STUDENT) {
                throw new TransactionException("user is not a student");
            }

            return user;
        }

        private Book fetchBook(TransactionRequest request){
            Book book = bookService.getBookByBookNo(request.getBookNo());
            if (book == null) {
                throw new TransactionException("Book not found");
            }

        return book;


    }

    public Integer returnBook(TransactionRequest transactionRequest) {
        User user = fetchUser(transactionRequest);
        Book book = fetchBook(transactionRequest);

        //only the user who has issued book should be returning that book

        if (book.getUser() != user) {
            throw new TransactionException("Book already issued by another user");
        }
        Transaction transaction= transactionRepository.findByUserEmailAndBookNo(transactionRequest.getUserEmail(),transactionRequest.getBookNo());
        LocalDate createdDate = transaction.getCreatedDate();
        long issueDate = createdDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        long currentDateAndTime=System.currentTimeMillis();
        long timeDifference=currentDateAndTime-issueDate;
        int days=(int)TimeUnit.MILLISECONDS.toDays(timeDifference);


//calculating fine if exceeds valid days
        int amount=0;
        if(days>validDays){
            int fine=(days-validDays)*finePerDay;
            if(fine> Math.abs(transaction.getSettlementAmount())){
                amount=fine- transaction.getSettlementAmount();

                transaction.setSettlementAmount(-fine);
            }
            else{
                amount=fine-Math.abs(transaction.getSettlementAmount());
                transaction.setSettlementAmount(-fine);
            }
            amount = fine-Math.abs(transaction.getSettlementAmount());
            transaction.setSettlementAmount(-fine);
            transaction.setTransactionStatus(TransactionStatus.FINED);


        }
        //not exceeding valid days
        else{
            transaction.setTransactionStatus(TransactionStatus.RETURNED);
            amount= transaction.getSettlementAmount();
            transaction.setSettlementAmount(0);

        }
        transactionRepository.save(transaction);
        book.setUser(null);

        return amount;

    }
}
