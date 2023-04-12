package com.aruna.controller;

import com.aruna.model.AuthenticationRequest;
import com.aruna.model.User;
import com.aruna.service.UserMaintainService;
import com.aruna.utilities.ApiResponse;
import com.aruna.utilities.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRegistrationController {
    @Autowired
    private UserMaintainService userMaintainService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> userRegistration(@RequestBody User user) throws Exception {
        ApiResponse<User> response = null;
        User userResponse =  userMaintainService.createUser(user);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response=new ApiResponse<>(true, userResponse);
         }

        return ResponseEntity.ok(response);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        AuthenticationResponse authenticationResponse = userMaintainService.authenticate(request);
        ApiResponse<AuthenticationResponse> response = new ApiResponse<>(true, authenticationResponse);

        return ResponseEntity.ok(response);
    }


}
