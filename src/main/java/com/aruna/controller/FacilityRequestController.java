package com.aruna.controller;

import com.aruna.model.Facility;
import com.aruna.model.FacilityDetails;
import com.aruna.service.FacilityRequestService;
import com.aruna.utilities.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/facility")
public class FacilityRequestController {
    @Autowired
    private FacilityRequestService facilityRequestService;

    @PostMapping("/loan")
    public ResponseEntity<ApiResponse> facilityRequest(@RequestBody Facility facility) throws Exception {
        ApiResponse<Facility> response = null;
        Facility userResponse =  facilityRequestService.facilityRequest(facility);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response=new ApiResponse<>(true, userResponse);
         }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/loan/{customerId}")
    public ResponseEntity<ApiResponse<FacilityDetails>> facilityDetailsByCustomerId(@PathVariable Integer customerId) throws Exception {
        ApiResponse<FacilityDetails> response = null;
        FacilityDetails userResponse =  facilityRequestService.getFacilityDetails(customerId);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response=new ApiResponse<>(true, userResponse);
         }
        return ResponseEntity.ok(response);
    }

}
