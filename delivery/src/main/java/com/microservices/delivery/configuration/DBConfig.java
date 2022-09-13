package com.microservices.delivery.configuration;

import com.microservices.delivery.entity.Address;
import com.microservices.delivery.entity.Delivery;
import com.microservices.delivery.entity.Product;
import com.microservices.delivery.entity.User;
import com.microservices.delivery.service.DeliveryService;
import com.microservices.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class DBConfig {

    private final UserService userService;
    private final DeliveryService deliveryService;

    @Bean
    public boolean instatiateDatabase() {
//        User(String username, String email)
        User user1 = new User("ighorbruno", "ighor@email.com");

//        Address(String streetName, String state, String city)
        Address add1 = new Address("Rua Zenith", "RJ", "Nova Iguaçu");
        user1.setAddress(add1);

        User user2 = new User("ibugrunu", "ibu@email.com");
        Address add2 = new Address("Rua dos Bobots", "RJ", "New Iguaçu");
        user2.setAddress(add2);

        userService.saveAll(Arrays.asList(user1, user2));

//        Product(String name, String description, Integer quantity, Double price)
        Product p1 = new Product("O nome do Vento", "Primeiro livro da crônica do matador de rei", 5, 59.99);
        Product p2 = new Product("Elden Ring", "Jogo super díficil - PS4", 1, 299.99);
        Product p3 = new Product("Guitarra", "A guitarra do Steve Vai linda pra caraca", 2, 4990.90);

        Delivery d1 = new Delivery();
        d1.addProducts(p1, p2, p3);
        d1.setUser(user1);

        Delivery d2 = new Delivery();
        p1 = new Product("O nome do Vento", "Primeiro livro da crônica do matador de rei", 1, 59.99);
        p2 = new Product("Elden Ring", "Jogo super díficil - PS4", 2, 299.99);
        d2.addProducts(p1, p2);
        d2.setUser(user2);

        Delivery d3 = new Delivery();
        p1 = new Product("O nome do Vento", "Primeiro livro da crônica do matador de rei", 1, 59.99);
        p2 = new Product("Elden Ring", "Jogo super díficil - PS4", 1, 299.99);
        d3.addProducts(p1, p2);
        d3.setUser(user2);

        deliveryService.saveAll(Arrays.asList(d1, d2, d3));

        return true;
    }
}
