package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.BAD_REQUEST,
    reason = "Member has reached the maximum number of borrowed books"
)
public class BorrowedLimitException extends RuntimeException{

    public BorrowedLimitException(String message) {
        super(message);
    }

}
