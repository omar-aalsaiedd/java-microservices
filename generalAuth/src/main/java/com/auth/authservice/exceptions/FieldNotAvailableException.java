package com.auth.authservice.exceptions;

import lombok.Getter;

@Getter
public class FieldNotAvailableException extends RuntimeException {
    private final String field;

    public FieldNotAvailableException(String field) {
        super(field);
        this.field = field;
    }
}
