package com.aruna.repository;

import com.aruna.entity.AccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<AccountTransactionEntity, Integer> {

     List<AccountTransactionEntity> findByAccountIdFkOrderByTransactionDateDesc(Integer accountId);
}
