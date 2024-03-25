package com.kb.api.exceptions;

public class CreditDemandLinkingImpossibleException extends RuntimeException {

    public CreditDemandLinkingImpossibleException(String message) {
        super(message);
    }

    public CreditDemandLinkingImpossibleException(String message, Throwable cause) {
        super(message, cause);
    }
}
