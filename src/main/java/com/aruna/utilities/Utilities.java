package com.aruna.utilities;


import com.aruna.entity.AccountTransactionEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Utilities {

    public static String getAccountNo(String prefix) {

        // Get current timestamp in milliseconds
        long timestamp = System.currentTimeMillis();
        // Generate a random 4-digit number
        Random random = new Random();
        int randomNumber = random.nextInt(9000) + 1000;
        // Combine timestamp and random number to create unique account number
        String accountNumber = null;
        if (Optional.ofNullable(prefix).isPresent()) {
            accountNumber = prefix+timestamp + "-" + randomNumber;
        }else {
            accountNumber=timestamp + "-" + randomNumber;
        }
        return accountNumber;

    }

 public static AccountTransactionEntity getTransactionDetails(BigDecimal transactionAmount,
                                                              Integer accountId, String transactionType, String description) {
        return AccountTransactionEntity.builder()
              .transactionDescription(description)
                .transactionAmount(transactionAmount)
                .transactionDate(LocalDateTime.now())
                .transactionType(transactionType)
                .createDate(LocalDateTime.now())
                .accountIdFk(accountId)
                .build();

    }
}
