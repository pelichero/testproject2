package com.nubank.testingproject.domain.out;

import lombok.Getter;

public enum Violation {
    ACCOUNT_NOT_ACTIVE("Account not active."), FIRST_TRANSACTION_ABOVE_THRESHOLD("First transaction above threshold.");

    @Getter
    private final String description;

    Violation(String description) {
        this.description = description;
    }
}
