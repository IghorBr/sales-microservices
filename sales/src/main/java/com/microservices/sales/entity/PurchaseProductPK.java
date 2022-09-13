package com.microservices.sales.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter @Setter
public class PurchaseProductPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseProductPK that = (PurchaseProductPK) o;
        return Objects.equals(purchase, that.purchase) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchase, product);
    }
}
