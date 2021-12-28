package com.nubank.testingproject.core;

import com.nubank.testingproject.domain.in.Account;
import com.nubank.testingproject.domain.in.Transaction;
import com.nubank.testingproject.domain.out.AuthorizerResult;
import org.springframework.stereotype.Service;

@Service
public interface AuthorizerUseCase {

    AuthorizerResult authorize(Transaction transaction, Account account);

}
