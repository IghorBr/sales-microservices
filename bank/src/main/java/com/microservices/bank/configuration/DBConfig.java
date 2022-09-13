package com.microservices.bank.configuration;

import com.microservices.bank.entity.Account;
import com.microservices.bank.entity.User;
import com.microservices.bank.service.AccountService;
import com.microservices.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DBConfig {

    private final UserService userService;

    @Bean
    public boolean instatiateDatabase() {
//        User(String username, String name, String lastName, String email, String password)
        User user1 = new User("ighorbruno", "Ighor", "Bruno", "ighor@email.com", "123");

//        Account(BigDecimal balance, BigDecimal limit)
        Account acc1 = new Account(BigDecimal.valueOf(15000), null);
        user1.setAccount(acc1);

        User user2 = new User("ibugrunu", "Ibu", "Grunu", "ibu@email.com", "123");
        Account acc2 = new Account(BigDecimal.valueOf(10000), null);
        user2.setAccount(acc2);

        userService.saveAll(Arrays.asList(user1, user2));

        return true;
    }

}
