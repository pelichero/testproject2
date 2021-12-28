package com.nubank.testingproject.service;

import com.nubank.testingproject.core.AuthorizerUseCase;
import com.nubank.testingproject.domain.in.Account;
import com.nubank.testingproject.domain.in.StatusAccount;
import com.nubank.testingproject.domain.in.Transaction;
import com.nubank.testingproject.domain.out.AuthorizerResult;
import com.nubank.testingproject.domain.out.Violation;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AuthorizerBusinessImpl implements AuthorizerUseCase {

    public static final BigDecimal NINETY_PERCENT = new BigDecimal("0.9");

    @Override
    public AuthorizerResult authorize(Transaction transaction, Account account) {

        List<Violation> violationList = new ArrayList<>();

        violationList.add(validateStatusAcc(account));
        violationList.add(validateExceededLimit(transaction, account));

        return new AuthorizerResult(account.getFinalAccount(transaction),violationList.stream().filter(Objects::nonNull).collect(Collectors.toList()));
    }

    private Violation validateStatusAcc(Account account) {

        if(StatusAccount.INACTIVE.equals(account.getStatus())){
            return Violation.ACCOUNT_NOT_ACTIVE;
        }

        return null;
    }

    private Violation validateExceededLimit(Transaction transaction, Account account) {

        if(CollectionUtils.isEmpty(account.getHistory())
            && transaction.getAmount().compareTo(account.getAvailableLimit().multiply(NINETY_PERCENT)) > 0){
            return Violation.FIRST_TRANSACTION_ABOVE_THRESHOLD;
        }

        return null;
    }
}
