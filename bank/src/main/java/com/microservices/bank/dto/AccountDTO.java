package com.microservices.bank.dto;

import com.microservices.bank.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter @Setter
public class AccountDTO extends BaseDTO {

    private String accountNumber;
    private LocalDate createdAt;
    private BigDecimal balance;
    private BigDecimal limite;
}
