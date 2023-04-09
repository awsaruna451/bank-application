package com.aruna.service;

import com.aruna.entity.AccountTransactionEntity;
import com.aruna.repository.TransactionRepository;
import com.aruna.utilities.CustomerException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    @Override
    public List<AccountTransactionEntity> getTransaction(Integer accountId) throws CustomerException {

         List<AccountTransactionEntity> transaction = transactionRepository.findByAccountIdFkOrderByTransactionDateDesc(accountId);

        return transaction;
    }
}
