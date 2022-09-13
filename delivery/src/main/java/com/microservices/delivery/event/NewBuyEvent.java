package com.microservices.delivery.event;

import com.microservices.delivery.dto.AddressDTO;
import com.microservices.delivery.dto.ProductDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class NewBuyEvent implements Serializable {

    private String username;
    private Set<ProductDTO> products;
}