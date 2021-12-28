package com.nubank.testingproject.domain.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String merchant;
    private BigDecimal amount;
    private LocalDateTime date = LocalDateTime.now();
}
