package com.microservices.sales.entity;

import com.microservices.sales.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Purchase extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOUGHT_AT")
    private LocalDate boughtAt;
    private Double discount = .0;

    @OneToMany(mappedBy = "id.purchase", cascade = CascadeType.ALL)
    private Set<PurchaseProduct> itens = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Purchase() {
        this.boughtAt = LocalDate.now();
    }

    public Purchase(User user) {
        this.user = user;
        this.boughtAt = LocalDate.now();
    }

    public Purchase addProduct(Product p, Integer amount) {
        PurchaseProduct pp = new PurchaseProduct(this, p, amount);
        itens.add(pp);
        return this;
    }

    public Double getTotal() {
        double amount = 0.0;
        for (PurchaseProduct pp : itens)
            amount += pp.getSubTotal();

        return amount - discount;
    }
}
