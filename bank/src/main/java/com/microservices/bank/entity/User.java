package com.microservices.bank.entity;

import com.microservices.bank.domain.BaseDomain;
import lombok.Getter;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    public User() {
        this.createdAt = LocalDate.now();
    }

    public User(String username, String name, String lastName, String email, String password) {
        this();
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
