package com.api.hateoas.exceptions;

import org.springframework.http.HttpStatus;

public class AuthInvalidValueException extends ApiException {
    public AuthInvalidValueException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
