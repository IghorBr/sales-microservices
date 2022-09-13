package com.microservices.sales.dto;

import com.microservices.sales.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductDTO extends BaseDTO {

    private String name;
    private String description;
    private Integer quantity;
    private Double price;

}
