package com.api.hateoas.exceptions;

import org.springframework.http.HttpStatus;

public class DataCuentaNullException extends ApiException{
    public DataCuentaNullException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
