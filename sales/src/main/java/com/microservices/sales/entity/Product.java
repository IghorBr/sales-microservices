package com.microservices.sales.entity;

import com.microservices.sales.domain.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Product extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
