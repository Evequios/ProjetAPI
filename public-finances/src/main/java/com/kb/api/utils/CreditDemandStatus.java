package com.kb.api.utils;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CreditDemandStatus {
    PENDING("PENDING"), REVIEWING("REVIEWING"), VALIDATION("VALIDATION"), ACCEPTED("ACCEPTED"), REFUSED("REFUSED");

    private final String currentstatus;

    CreditDemandStatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }

    @JsonValue
    public String getStatus() {
        return currentstatus;
    }
}
