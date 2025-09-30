package main.java.com.business.transaction;

import main.java.com.business.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public record Transaction(String transactionId ,LocalDateTime date,
                          BigDecimal amount, TransactionType type,
                          String place,String accountId) {

    public Transaction(BigDecimal amount, TransactionType type,
                       String place,String accountId){

        this(UUID.randomUUID().toString().substring(0,10),
                LocalDateTime.now(),
                amount, type,
                place, accountId);
    }

}
