package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.NOT_FOUND,
    reason = "Member not found"
)
public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException(String message) {
        super(message);
    }

}
