package com.microservices.delivery.dto;

import com.microservices.delivery.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {

    private String username;
    private String email;
    private AddressDTO address;
}
