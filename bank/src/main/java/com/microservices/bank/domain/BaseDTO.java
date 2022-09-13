package com.microservices.bank.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class BaseDTO implements Serializable {

    protected Long id;
}
