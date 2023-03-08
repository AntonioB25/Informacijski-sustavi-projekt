package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.BAD_REQUEST,
    reason = "Book already reserved or lended"
)
public class BookNotFreeException extends RuntimeException{

    public BookNotFreeException(String message) {
        super(message);
    }

}
