package se.salt.cartapibackend.cart.repository;

import org.springframework.data.repository.CrudRepository;
import se.salt.cartapibackend.cart.model.Cart;

public interface ICartRepository extends CrudRepository<Cart, String> {
}
