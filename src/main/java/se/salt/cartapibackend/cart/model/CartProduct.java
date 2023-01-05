package se.salt.cartapibackend.cart.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartProduct {
    @Id
    String id;

    @Column
    String name;

    @Column
    @EqualsAndHashCode.Exclude
    Integer quantity;

    @Column
    Double price;

    public void addToQuantity(Integer quantity){
        this.quantity += quantity;
    }
}
