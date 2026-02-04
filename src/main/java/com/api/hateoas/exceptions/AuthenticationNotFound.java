package com.api.hateoas.exceptions;

import org.springframework.http.HttpStatus;

public class AuthenticationNotFound extends ApiException{
    public AuthenticationNotFound(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
