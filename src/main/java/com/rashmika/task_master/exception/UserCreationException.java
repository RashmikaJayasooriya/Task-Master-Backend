package com.rashmika.task_master.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserCreationException extends RuntimeException {
    public UserCreationException(String message) {
        super(message);
    }
}
