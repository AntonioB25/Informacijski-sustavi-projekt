package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "Lending not found"
)
public class LendingNotFoundException extends RuntimeException {

    public LendingNotFoundException(String message) {
        super(message);
    }

}
