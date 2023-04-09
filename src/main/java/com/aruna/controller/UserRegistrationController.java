package com.aruna.controller;

import com.aruna.model.User;
import com.aruna.service.UserMaintainService;
import com.aruna.utilities.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserRegistrationController {
    @Autowired
    private UserMaintainService userMaintainService;

    @PostMapping
    public ResponseEntity<ApiResponse> userRegistration(@RequestBody User user, HttpServletRequest request) throws Exception {
        ApiResponse<User> response = null;
        User userResponse =  userMaintainService.createUser(user);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response=new ApiResponse<>(true, userResponse);
         }

        return ResponseEntity.ok(response);
    }
}
