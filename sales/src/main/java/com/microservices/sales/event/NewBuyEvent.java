package com.microservices.sales.event;

import com.microservices.sales.dto.ProductDTO;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor @AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class NewBuyEvent implements Serializable {

    private String username;
    private Set<ProductDTO> products;
}
