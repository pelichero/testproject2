package com.nubank.testingproject.domain.out;

import com.nubank.testingproject.domain.in.Account;
import lombok.Data;

import java.util.List;

@Data
public class AuthorizerResult {

    private Account account;
    private List<Violation> violations;

    public AuthorizerResult(Account account, List<Violation> violations) {
        this.account = account;
        this.violations = violations;
    }
}
