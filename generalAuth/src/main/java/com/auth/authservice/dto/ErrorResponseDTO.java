package com.auth.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private Object message;
    private HttpStatus status;
    private Integer statusCode;

    public ErrorResponseDTO(String message, HttpStatus status, Integer statusCode) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;

    }


}
