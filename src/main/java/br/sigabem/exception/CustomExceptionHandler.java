package br.sigabem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CepNotFoundException.class)
    public ResponseEntity<ApiError> handleCepNotFound(CepNotFoundException ex) {
        ApiError error = new ApiError();
        error.setErrors(Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex) {
        ApiError error = new ApiError();
        error.setErrors(Collections.singletonList("Erro interno: " + ex.getMessage()));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}