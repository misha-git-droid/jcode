package com.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ResponseError {
    private HttpStatus status;
    private String message;
    private LocalDateTime time = LocalDateTime.now();

    public ResponseError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
