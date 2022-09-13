package com.microservices.bank.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class BankExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(BankExceptionHandler.class);

    @ExceptionHandler(BankException.class)
    public ResponseEntity<StandardError> bankException(BankException e, HttpServletRequest request) {
        LOGGER.error(e.getMessage(), e);

        StandardError stdError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stdError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        LOGGER.error(e.getMessage(), e);

        StandardError stdError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stdError);
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<StandardError> invalidAmount(InvalidAmountException e, HttpServletRequest request) {
        LOGGER.error(e.getMessage(), e);

        StandardError stdError = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stdError);
    }
}
