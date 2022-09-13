package com.microservices.bank.dto;

import com.microservices.bank.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    private String name;
    private String lastName;
    private String email;
    private LocalDate createdAt;
    private AccountDTO account;
}
