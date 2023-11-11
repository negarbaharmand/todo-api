package com.baharmand.todoapi.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({DataNotFoundException.class, DataDuplicateException.class, IllegalArgumentException.class})
    public ResponseEntity<APIError> handleCustomExceptions(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new APIError(status, ex.getMessage()));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder details = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            details.append(error.getField());
            details.append(" ");
            details.append(error.getDefaultMessage());
            details.append(", ");
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIError(HttpStatus.BAD_REQUEST, details.toString()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleGlobalException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        APIError apiError = new APIError(status, "INTERNAL_ERROR: " + ex.getMessage());
        return ResponseEntity.status(status).body(apiError);
    }
}
