package com.dan.lab2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import jakarta.persistence.EntityExistsException;

/**
 * GlobalExceptionHandler handles exceptions thrown by the application
 * and returns appropriate HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NoSuchElementException and returns a 404 Not Found status with the exception message.
     *
     * @param ex the exception thrown when an element is not found
     * @return ResponseEntity with the error message and 404 status
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles EntityExistsException and returns a 409 Conflict status with the exception message.
     *
     * @param ex the exception thrown when an entity already exists
     * @return ResponseEntity with the error message and 409 status
     */
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<String> handleEntityExistsException(EntityExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * Handles MethodArgumentNotValidException and returns a 400 Bad Request status
     * with a validation error message.
     *
     * @param ex the exception thrown when method arguments fail validation
     * @return ResponseEntity with validation error message and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please, fill the required fields: name (must not be blank), price and stock (must not be negative or null).\n" + ex.getBindingResult());
    }

    /**
     * Handles generic exceptions and returns a 500 Internal Server Error status
     * with a generic error message.
     *
     * @param ex the generic exception thrown
     * @return ResponseEntity with the error message and 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
