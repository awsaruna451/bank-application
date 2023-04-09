package com.aruna.repository;

import com.aruna.entity.RentalFacilityScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalFacilityScheduleRepository extends JpaRepository<RentalFacilityScheduler, Integer> {


}
