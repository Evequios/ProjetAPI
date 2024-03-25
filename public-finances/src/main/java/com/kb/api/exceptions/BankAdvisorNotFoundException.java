package com.kb.api.exceptions;

public class BankAdvisorNotFoundException extends RuntimeException {

    public BankAdvisorNotFoundException(String message) {
        super(message);
    }

    public BankAdvisorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}