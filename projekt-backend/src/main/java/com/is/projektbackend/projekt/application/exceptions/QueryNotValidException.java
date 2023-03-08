package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.BAD_REQUEST,
    reason = "Invalid characters or words"
)
public class QueryNotValidException extends RuntimeException{

    public QueryNotValidException(String message) {
        super(message);
    }

}
