package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "Book with given id not found"
)
public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String message) {
        super(message);
    }

}
