package com.microservices.sales.dto;

import com.microservices.sales.domain.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class UserDTO extends BaseDTO {

    private String username;
    private String name;
    private String lastName;
    private String email;
    private String userType;
    private AddressDTO address;

}
