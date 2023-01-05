package se.salt.cartapibackend.product.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    final String id = UUID.randomUUID().toString();

    @NonNull
    @Column(nullable = false)
    String name;

    @NonNull
    @Column(nullable = false)
    String description;

    @NonNull
    @Column(nullable = false)
    Double price;

}
