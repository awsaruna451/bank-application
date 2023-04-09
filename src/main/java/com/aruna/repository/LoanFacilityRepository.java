package com.aruna.repository;

import com.aruna.entity.LoanFacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanFacilityRepository extends JpaRepository<LoanFacilityEntity, Integer> {

     LoanFacilityEntity findByFacilityNo(String loanNo);

}
