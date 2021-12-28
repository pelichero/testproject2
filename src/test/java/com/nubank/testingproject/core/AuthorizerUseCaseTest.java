package com.nubank.testingproject.core;

import com.nubank.testingproject.domain.in.Account;
import com.nubank.testingproject.domain.in.StatusAccount;
import com.nubank.testingproject.domain.in.Transaction;
import com.nubank.testingproject.domain.out.AuthorizerResult;
import com.nubank.testingproject.service.AuthorizerBusinessImpl;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class AuthorizerUseCaseTest {

    @Test
    public void testAuthorizer() {
        AuthorizerUseCase authorizerUseCase = new AuthorizerBusinessImpl();

        Transaction transaction = Transaction
                .builder()
                .merchant("Burguer King")
                .amount(BigDecimal.TEN)
                .build();

        Account account = Account
                .builder()
                .status(StatusAccount.ACTIVE)
                .availableLimit(BigDecimal.TEN.pow(2))
                .build();

        AuthorizerResult authorizerResult = authorizerUseCase.authorize(transaction, account);

        assertEquals("Correct available limit.", new BigDecimal("90"), authorizerResult.getAccount().getAvailableLimit());
        assertTrue("Has violations.", CollectionUtils.isEmpty(authorizerResult.getViolations()));
    }


}