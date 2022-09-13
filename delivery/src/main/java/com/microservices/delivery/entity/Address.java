package com.microservices.delivery.entity;

import com.microservices.delivery.domain.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Address extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "STREET_NAME")
    private String streetName;
    private String state;
    private String city;

    public Address(String streetName, String state, String city) {
        this.streetName = streetName;
        this.state = state;
        this.city = city;
    }
}
