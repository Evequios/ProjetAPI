package com.kb.api.exceptions;

public class CreditDemandNotFoundException extends RuntimeException{
    
    public CreditDemandNotFoundException(String message) {
        super(message);
    }

    public CreditDemandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
