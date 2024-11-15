package com.svcbackend.exception;

import com.svcbackend.response.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse<Object>> handleAllException(Exception ex, WebRequest request) {
        log.error("Response Error: {}", request.toString());
        GenericResponse<Object> err = new GenericResponse<>(false, ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error("Validation Error: {}", ex.getMessage());
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(e -> e.getCode().concat(": ").concat(Objects.requireNonNull(e.getDefaultMessage())))
                .collect(Collectors.joining(", "));
        GenericResponse<Object> err = new GenericResponse<>(false, message);
        ex.printStackTrace();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<GenericResponse<Object>> handleSqlException(SQLException ex) {
        log.error("SQL Error: {}", ex.getMessage());
        GenericResponse<Object> err = new GenericResponse<>(false, ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }
}