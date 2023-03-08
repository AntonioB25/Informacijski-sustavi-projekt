package com.is.projektbackend.projekt.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    value = HttpStatus.BAD_REQUEST,
    reason = "Member is not active"
)
public class MemberNotActiveException extends  RuntimeException{

    public MemberNotActiveException(String message) {
        super(message);
    }

}
