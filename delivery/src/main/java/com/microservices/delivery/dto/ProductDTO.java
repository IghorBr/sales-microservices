package com.microservices.delivery.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private String description;
    private Integer quantity;
    private Double price;
}
