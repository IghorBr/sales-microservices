package com.microservices.bank.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter @Setter
public class NewBuyDTO implements Serializable {

    private String username;
    private String password;
    private BigDecimal total;
}
