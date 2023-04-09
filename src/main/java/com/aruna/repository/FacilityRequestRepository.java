package com.aruna.repository;

import com.aruna.entity.FacilityRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRequestRepository extends JpaRepository<FacilityRequestEntity, Integer> {


}
