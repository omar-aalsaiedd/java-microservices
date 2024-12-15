package com.auth.authservice.exceptions;

import com.auth.authservice.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FieldNotAvailableException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(FieldNotAvailableException ex, HttpServletRequest request) {


        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE,
                HttpStatus.NOT_ACCEPTABLE.value());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDTO);
    }

}
