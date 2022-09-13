package com.microservices.delivery.dto;

import com.microservices.delivery.domain.BaseDTO;
import com.microservices.delivery.entity.Delivery;
import com.microservices.delivery.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class DeliveryDTO extends BaseDTO {

    private LocalDate expectedDate;
    private LocalDate leaveAt;
    private LocalDate arrivedAt;
    private String status;

    private UserDTO user;

    private Set<Product> products;
}
