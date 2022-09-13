package com.microservices.bank.service;

import com.microservices.bank.domain.BaseService;
import com.microservices.bank.dto.NewBuyDTO;
import com.microservices.bank.entity.Account;

import java.math.BigDecimal;

public interface AccountService extends BaseService<Account> {
    void deposit(String username, BigDecimal amount);

    void withdraw(String username, BigDecimal amount);

    boolean newBuy(NewBuyDTO dto);
}
