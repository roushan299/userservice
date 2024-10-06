package com.roushan.userservice.exceptions;

import com.roushan.userservice.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundExceptions resourceNotFoundExceptions) {
            String message = resourceNotFoundExceptions.getMessage();
            ApiResponse apiResponse = ApiResponse.builder()
                    .message(message)
                    .success(true)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
