package com.aruna.repository;

import com.aruna.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

     AccountEntity findByCustomerIdFk(Integer customerIdFk);
}
