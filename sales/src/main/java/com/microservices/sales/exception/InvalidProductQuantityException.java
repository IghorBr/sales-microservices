package com.microservices.sales.exception;

public class InvalidProductQuantityException extends SalesException {

    public InvalidProductQuantityException(String message) {
        super(message);
    }

    public InvalidProductQuantityException(String message, Throwable cause) {
        super(message, cause);
    }
}
