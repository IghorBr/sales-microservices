package com.microservices.sales.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class ProductQuantityDTO implements Serializable {

    private Long productId;
    private Integer quantity;
}
