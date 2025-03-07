package com.marcinsz.recruitingtask03.exception;

import com.marcinsz.recruitingtask03.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GitHubUserNotExistException.class)
    public ResponseEntity<ErrorResponse> gitHubUserNotExistHandler(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .build()
        );
    }
}
