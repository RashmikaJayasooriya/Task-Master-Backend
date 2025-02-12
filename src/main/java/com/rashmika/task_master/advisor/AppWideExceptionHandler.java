package com.rashmika.task_master.advisor;

import com.rashmika.task_master.exception.AuthenticationException;
import com.rashmika.task_master.exception.TaskNotFoundException;
import com.rashmika.task_master.exception.UserCreationException;
import com.rashmika.task_master.exception.UserNotFoundException;
import com.rashmika.task_master.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<StandardResponse> handleTaskNotFoundException(TaskNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Error", e.getMessage()), HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<StandardResponse> handleUserCreationException(UserCreationException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400, "An error occurred while creating account", e.getMessage()), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardResponse> handleAuthenticationException(AuthenticationException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(401, "Authentication failed", e.getMessage()), HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardResponse> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Error", e.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}
