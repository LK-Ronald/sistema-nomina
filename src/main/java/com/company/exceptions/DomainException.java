package com.company.exceptions;

public class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
