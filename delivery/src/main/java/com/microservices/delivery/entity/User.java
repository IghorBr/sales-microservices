package com.microservices.delivery.entity;

import com.microservices.delivery.domain.BaseDomain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Getter @Setter
@NoArgsConstructor
public class User extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    private String username;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address address;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
