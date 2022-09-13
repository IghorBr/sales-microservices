package com.microservices.sales.dto;

import com.microservices.sales.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
public class PurchaseDTO extends BaseDTO {

    private LocalDate boughtAt;
    private Double discount;
    private Double total;
    private UserDTO user;

    private Set<PurchaseProductDTO> itens;
}
