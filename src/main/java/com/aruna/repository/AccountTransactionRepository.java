package com.aruna.repository;


import com.aruna.entity.AccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactionEntity, Integer> {
}
