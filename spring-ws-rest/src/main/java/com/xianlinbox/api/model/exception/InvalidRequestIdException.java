package com.xianlinbox.api.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request id must less than 10")
public class InvalidRequestIdException extends RuntimeException {
    public InvalidRequestIdException(String message) {
        super(message);
    }
}

