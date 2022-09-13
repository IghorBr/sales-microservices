package com.microservices.delivery.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter @Setter
public class Product {

    private String name;
    private String description;
    private Integer quantity;
    private Double price;

    public Product(String name, String description, Integer quantity, Double price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}
