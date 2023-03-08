package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    code = HttpStatus.NOT_FOUND,
    reason = "Section not found"
)
public class SectionNotFoundException extends RuntimeException{

    public SectionNotFoundException(String message) {
        super(message);
    }

}
