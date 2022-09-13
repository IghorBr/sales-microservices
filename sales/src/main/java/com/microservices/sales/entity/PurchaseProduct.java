package com.microservices.sales.entity;

import com.microservices.sales.exception.InvalidProductQuantityException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter @Setter
public class PurchaseProduct {

    @EmbeddedId
    private PurchaseProductPK id = new PurchaseProductPK();

    private Integer quantity;

    public Double getPrice() {
        return id.getProduct().getPrice();
    }

    public Double getSubTotal() {
        return id.getProduct().getPrice() * quantity;
    }

    public Purchase getPurchase() {
        return this.id.getPurchase();
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public PurchaseProduct(Purchase purchase, Product product, Integer quantity) {
        if (product.getQuantity() - quantity < 0)
            throw new InvalidProductQuantityException(product.getName() + " possui apenas " + product.getQuantity() + " unidades em estoque");

        this.id.setPurchase(purchase);
        this.id.setProduct(product);
        this.quantity = quantity;

        product.setQuantity(product.getQuantity() - quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseProduct that = (PurchaseProduct) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
