package com.aruna.controller;

import com.aruna.model.Facility;
import com.aruna.service.FacilityRequestService;
import com.aruna.utilities.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/facility")
@CrossOrigin(origins = "*")
public class FacilityRequestController {
    @Autowired
    private FacilityRequestService facilityRequestService;

    @PostMapping
    public ResponseEntity<ApiResponse> facilityRequest(@RequestBody Facility facility, HttpServletRequest request) throws Exception {
        ApiResponse<Facility> response = null;
        Facility userResponse =  facilityRequestService.facilityRequest(facility);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response=new ApiResponse<>(true, userResponse);
         }
        return ResponseEntity.ok(response);
    }

}
