package com.whosricardo.gymbookingapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private int status;
    private String error;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String error) {
        this.status = status.value();
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }
}
