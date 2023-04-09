package com.aruna.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = { CustomerException.class })
    public ResponseEntity<Object> handleNotFoundException(CustomerException ex, WebRequest request) {
        String errorMessage = "Entity not found: " + ex.getMessage();
        ApiError apiError = ApiError.builder()
                .message(errorMessage)
                .status(HttpStatus.NOT_IMPLEMENTED)
                .timestamp(LocalDateTime.now())
                .build();
        ApiResponse response = new ApiResponse<>(false,apiError);
        return new ResponseEntity<>(response,HttpStatus.NOT_IMPLEMENTED);
    }

}

