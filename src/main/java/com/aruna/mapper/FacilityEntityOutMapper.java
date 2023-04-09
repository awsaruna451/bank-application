package com.aruna.mapper;

import com.aruna.entity.FacilityRequestEntity;
import com.aruna.model.Facility;
import org.springframework.stereotype.Component;

@Component
public class FacilityEntityOutMapper {

    public Facility map(FacilityRequestEntity facilityRequestEntity) {
        return Facility.builder()
                .facilityType(facilityRequestEntity.getType())
                .facilityRate(facilityRequestEntity.getRate())
                .facilityStatus(facilityRequestEntity.getStatus())
                .customerIdFk(facilityRequestEntity.getCustomerIdFk())
                .requestId(facilityRequestEntity.getRequestIdPk())
                .facilityAmount(facilityRequestEntity.getAmount())
                .facilityTerm(facilityRequestEntity.getTerm())
                .facilityRequestDate(facilityRequestEntity.getRequestDate())
                .facilityApproveDate(facilityRequestEntity.getApprovedDate())
                .purpose(facilityRequestEntity.getPurpose())
                .build();
    }


}
