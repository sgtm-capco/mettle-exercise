package com.mettle.exercise.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Error {
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private List<String> details;

    public Error(HttpStatus httpStatus, String message, List<String> details) {
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
