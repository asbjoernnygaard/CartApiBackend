package se.salt.cartapibackend.cart.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.salt.cartapibackend.cart.model.Cart;
import se.salt.cartapibackend.cart.model.CartProduct;

import java.util.NoSuchElementException;

@Repository
public class CartRepository {

    @Autowired
    ICartRepository repo;

    public Cart createEmptyCart() {
        return repo.save(new Cart());
    }

    public Cart getCartById(String cartId) {
        return repo.findById(cartId).orElseThrow(
                () -> new NoSuchElementException("No cart was found with the given id"));
    }

    public void deleteCartById(String cartId) {
        getCartById(cartId);
        repo.deleteById(cartId);
    }

    public Cart updateCart(Cart cart) {
        return repo.save(cart);
    }
}
