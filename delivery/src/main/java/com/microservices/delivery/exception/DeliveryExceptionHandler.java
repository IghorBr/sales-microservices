package com.microservices.delivery.exception;

import com.microservices.delivery.event.EventHandler;
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
public class DeliveryExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(DeliveryExceptionHandler.class);

    @ExceptionHandler(DeliveryException.class)
    public ResponseEntity<StandardError> salesException(DeliveryException e, HttpServletRequest request) {
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
}
