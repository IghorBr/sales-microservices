package com.microservices.sales.dto;

import com.microservices.sales.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AddressDTO extends BaseDTO {

    private String streetName;
    private String state;
    private String city;
}
