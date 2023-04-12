package com.aruna.repository;

import com.aruna.entity.LoanFacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanFacilityRepository extends JpaRepository<LoanFacilityEntity, Integer> {

     LoanFacilityEntity findByFacilityNo(String loanNo);
     List<LoanFacilityEntity> findByCustomerIdFk(Integer customerId);

}
