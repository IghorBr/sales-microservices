package com.microservices.delivery.entity;

import com.microservices.delivery.domain.BaseDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Delivery extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(name = "EXPECTED_DATE")
    private LocalDate expectedDate;

    @Column(name = "LEAVE_AT")
    private LocalDate leaveAt;

    @Column(name = "ARRIVED_AT")
    private LocalDate arrivedAt;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;

    @ElementCollection
    @CollectionTable(name="PRODUCTS", joinColumns = @JoinColumn(name = "DELIVERY_ID"))
    private Set<Product> products = new HashSet<>();

    public Delivery() {
        this.expectedDate = LocalDate.now();
        this.leaveAt = LocalDate.now();
        this.arrivedAt = LocalDate.now();
        this.status = DeliveryStatus.RECEIVED;
    }

    public Delivery addProducts(Product... p) {
        for (Product product : p) {
            this.products.add(product);
        }

        return this;
    }

    @RequiredArgsConstructor
    @Getter
    public enum DeliveryStatus {
        RECEIVED("Pedido recebido"),
        LEAVED("Saiu para entrega"),
        ON_THE_WAY("À caminho"),
        ARRIVED("Pedido entregue");

        private final String description;
    }
}
