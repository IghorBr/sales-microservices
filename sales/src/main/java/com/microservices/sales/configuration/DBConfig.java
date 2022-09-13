package com.microservices.sales.configuration;

import com.microservices.sales.entity.Address;
import com.microservices.sales.entity.Product;
import com.microservices.sales.entity.Purchase;
import com.microservices.sales.entity.User;
import com.microservices.sales.service.ProductService;
import com.microservices.sales.service.PurchaseService;
import com.microservices.sales.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DBConfig {

    private final UserService userService;
    private final ProductService productService;
    private final PurchaseService purchaseService;

    @Bean
    public boolean instantiateDevDatabase() {
//        User(String username, String name, String lastName, String email, String password, User.UserType userType)
        User user1 = new User("ighorbruno", "Ighor", "Bruno", "ighor@email.com", "123", User.UserType.GOD);

//        Address(String streetName, String state, String city)
        Address add1 = new Address("Rua Zenith", "RJ", "Nova Iguaçu");
        user1.setAddress(add1);

        User user2 = new User("ibugrunu", "Ibu", "Grunu", "ibu@email.com", "123", User.UserType.ADMIN);
        Address add2 = new Address("Rua dos Bobots", "RJ", "New Iguaçu");
        user2.setAddress(add2);

        userService.saveAll(Arrays.asList(user1, user2));

//        Product(String name, String description, Integer quantity, Double price)
        Product p1 = new Product("O nome do Vento", "Primeiro livro da crônica do matador de rei", 20, 59.99);
        Product p2 = new Product("Elden Ring", "Jogo super díficil - PS4", 10, 299.99);
        Product p3 = new Product("Guitarra", "A guitarra do Steve Vai linda pra caraca", 3, 4990.90);

        productService.saveAll(Arrays.asList(p1, p2, p3));

//        Purchase(User user)
        Purchase purch1 = new Purchase(user1);
        purch1.addProduct(p1, 5);
        purch1.addProduct(p2, 1);
        purch1.addProduct(p3, 2);

        Purchase purch2 = new Purchase(user2);
        purch2.addProduct(p1, 1);
        purch2.addProduct(p2, 2);

        Purchase purch3 = new Purchase(user2);
        purch3.addProduct(p1, 1);
        purch3.addProduct(p2, 1);

        productService.saveAll(Arrays.asList(p1, p2, p3));
        purchaseService.saveAll(Arrays.asList(purch1, purch2, purch3));

        return true;
    }

}
