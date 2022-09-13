package com.microservices.sales.entity;

import com.microservices.sales.domain.BaseDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
@Getter @Setter
public class User extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true)
    private String username;

    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(name = "CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "USER_TYPE")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    public User(String username, String name, String lastName, String email, String password, UserType userType) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.createdAt = LocalDate.now();
    }

    public User() {
        this.createdAt = LocalDate.now();
    }

    @RequiredArgsConstructor
    @Getter
    public enum UserType {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN"),
        GOD("ROLE_GOD");

        private final String description;
    }
}
