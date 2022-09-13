package com.microservices.sales.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class NewPurchaseDTO implements Serializable {

    private Double discount = 0.;
    private List<ProductQuantityDTO> itens;

}
