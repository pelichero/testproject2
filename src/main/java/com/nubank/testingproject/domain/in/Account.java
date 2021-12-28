package com.nubank.testingproject.domain.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.LinkedList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private StatusAccount status;
    private BigDecimal availableLimit;
    private LinkedList<Transaction> history = new LinkedList<>();

    public Account getFinalAccount(Transaction transaction){
        Account account = new Account();

        account.setStatus(this.status);
        LinkedList<Transaction> history = account.getHistory();
        if(CollectionUtils.isEmpty(history)){
            history = new LinkedList<>();
        }
        history.add(transaction);

        if(!CollectionUtils.isEmpty(history)){
            account.setAvailableLimit(availableLimit.subtract(history.stream().map(Transaction::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)));
        }

        return account;
    }
}
