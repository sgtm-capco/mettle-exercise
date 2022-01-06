package com.mettle.exercise.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error(HttpStatus.BAD_REQUEST, "Validation Error", ex.getBindingResult().getAllErrors().stream().map(er -> er.getDefaultMessage()).collect(Collectors.toList()));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error", List.of(ex.getLocalizedMessage()));
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundExceptions(RecordNotFoundException ex, WebRequest request) {
        Error error = new Error(HttpStatus.NOT_FOUND, "Record Not Found", List.of(ex.getLocalizedMessage()));
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }
}
