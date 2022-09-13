package com.microservices.bank.entity;

import com.microservices.bank.domain.BaseDomain;
import com.microservices.bank.exception.InvalidAmountException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Getter @Setter
public class Account extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    private BigDecimal balance = BigDecimal.ZERO;

    private BigDecimal limite;

    public Account() {
        this.createdAt = LocalDate.now();
        this.accountNumber = getRandomString(7);
    }

    public Account(BigDecimal balance, BigDecimal limit) {
        this();
        this.balance = balance;
        this.limite = limit;
    }

    public Account withdraw(BigDecimal value) {
        BigDecimal subtract = this.balance.subtract(value);

        if (subtract.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidAmountException("Impossível sacar essa quantia: seu saldo atual é: " + this.balance);

        this.balance = subtract;
        return this;
    }

    public Account deposit(BigDecimal value) {
        this.balance = this.balance.add(value);
        return this;
    }

    private String getRandomString(int n) {
        String numericString = "0123456789";

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        for (int i = 0; i < n+1; i++) {
            if (i == n-1) {
                sb.append("-");
                continue;
            }

            int index = (random.nextInt(10));
            sb.append(numericString.charAt(index));
        }

        return sb.toString();
    }
}
