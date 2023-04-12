package com.aruna.service;

import com.aruna.entity.LoanFacilityEntity;
import com.aruna.model.Facility;
import com.aruna.model.FacilityDetails;

import java.util.List;

public interface FacilityRequestService {

    Facility facilityRequest(Facility facility) throws Exception;
    FacilityDetails getFacilityDetails(Integer customerId) throws Exception;
}
