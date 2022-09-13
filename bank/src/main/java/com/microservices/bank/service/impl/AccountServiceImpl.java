package com.microservices.bank.service.impl;

import com.microservices.bank.domain.BaseRepository;
import com.microservices.bank.domain.BaseServiceImpl;
import com.microservices.bank.dto.NewBuyDTO;
import com.microservices.bank.entity.Account;
import com.microservices.bank.entity.User;
import com.microservices.bank.exception.BankException;
import com.microservices.bank.exception.InvalidAmountException;
import com.microservices.bank.exception.ObjectNotFoundException;
import com.microservices.bank.repository.AccountRepository;
import com.microservices.bank.service.AccountService;
import com.microservices.bank.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository, UserService userService) {
        super(accountRepository);
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    @Override
    public void deposit(String username, BigDecimal amount) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        Account account = user.getAccount();
        account.deposit(amount);

        accountRepository.save(account);
    }

    @Override
    public void withdraw(String username, BigDecimal amount) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        Account account = user.getAccount();
        account.withdraw(amount);

        accountRepository.save(account);
    }

    @Override
    public boolean newBuy(NewBuyDTO dto) {
        User user = userService.findByUsername(dto.getUsername()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        if (!user.getPassword().equals(dto.getPassword()))
            throw new BankException("As senhas não são as mesmas");

        BigDecimal limite = user.getAccount().getLimite();
        if (Objects.nonNull(limite) && dto.getTotal().compareTo(limite) > 0) {
            throw new InvalidAmountException("O Valor está acima do limite");
        }

        boolean canBuy = user.getAccount().getBalance().compareTo(dto.getTotal()) > 0;

        if (canBuy) {
            this.withdraw(user.getUsername(), dto.getTotal());
        }

        return canBuy;
    }
}
