package se.salt.cartapibackend.product.model;

import lombok.Value;

@Value
public class CreateProductDTO {
    String name;
    String description;
    Double price;
}
