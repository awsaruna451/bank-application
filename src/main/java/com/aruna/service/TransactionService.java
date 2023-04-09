package com.aruna.service;


import com.aruna.entity.AccountTransactionEntity;
import com.aruna.utilities.CustomerException;

import java.util.List;

public interface TransactionService {

    List<AccountTransactionEntity> getTransaction(Integer accountId) throws CustomerException;
}
