package se.salt.cartapibackend.cart.model;

import lombok.Value;

@Value
public class AddCartProductDTO {
    String id;
    Integer quantity;
}
