package com.microservices.delivery.dto;

import com.microservices.delivery.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO extends BaseDTO {

    private String streetName;
    private String state;
    private String city;
}
