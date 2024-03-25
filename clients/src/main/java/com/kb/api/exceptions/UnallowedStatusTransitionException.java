package com.kb.api.exceptions;

public class UnallowedStatusTransitionException extends RuntimeException{
    
    public UnallowedStatusTransitionException(String message) {
        super(message);
    }

    public UnallowedStatusTransitionException(String message, Throwable cause) {
        super(message, cause);
    }
}
