package com.microservices.sales.dto;

import com.microservices.sales.domain.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProductDTO {

    private Integer quantity;
    private Double price;

    private Long productId;
}
