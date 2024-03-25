package com.kb.api.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditDemandStatus {
    PENDING("Pending"), REVIEWING("Reviewing"), VALIDATION("Validation"), ACCEPTED("Accepted"), REFUSED("Refused");

    private final String currentstatus;

    CreditDemandStatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    @JsonValue
    public String getStatus() {
        return currentstatus;
    }
}
