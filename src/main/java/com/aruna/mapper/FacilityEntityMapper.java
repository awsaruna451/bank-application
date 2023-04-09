package com.aruna.mapper;

import com.aruna.entity.FacilityRequestEntity;
import com.aruna.model.Facility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FacilityEntityMapper {

    @Value("${facility.request.type}")
    private String facilityType;
    @Value("${facility.request.rate}")
    private Float rate;

    public FacilityRequestEntity map(Facility facility) {
        return FacilityRequestEntity.builder()
                .type(facilityType)
                .rate(rate)
                .status(facility.getFacilityStatus())
                .customerIdFk(facility.getCustomerIdFk())
                .amount(facility.getFacilityAmount())
                .term(facility.getFacilityTerm())
                .approvedDate(facility.getFacilityApproveDate())
                .requestDate(facility.getFacilityRequestDate())
                .approvedUser(facility.getFacilityApproveUser())
                .purpose(facility.getPurpose())
                .build();
    }


}
