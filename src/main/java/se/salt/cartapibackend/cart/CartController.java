package se.salt.cartapibackend.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salt.cartapibackend.cart.model.AddCartProductDTO;
import se.salt.cartapibackend.cart.model.Cart;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @PostMapping
    ResponseEntity<?> createEmptyCart() {
        Cart newCart = service.createEmptyCart();
        return ResponseEntity.created(URI.create("/cart/" + newCart.getId())).build();
    }

    @GetMapping("/{cartId}")
    ResponseEntity<?> getCartById(@PathVariable String cartId) {
        try {
            Cart cart = service.getCartById(cartId);
            return ResponseEntity.ok(cart);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cartId}")
    ResponseEntity<?> deleteCartById(@PathVariable String cartId) {
        try {
            service.deleteCartById(cartId);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cartId}")
    ResponseEntity<Cart> addCartProductToCart(@PathVariable String cartId, @RequestBody AddCartProductDTO dto){
        Cart updatedCart = service.addCartProductToCart(cartId, dto);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{cartId}/{cartProductId}")
    ResponseEntity<?> removeCartProductFromCart(@PathVariable String cartId, @PathVariable String cartProductId){
        try {
            Cart updatedCart = service.removeCartProductFromCart(cartId, cartProductId);
            return ResponseEntity.ok(updatedCart);

        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }
    }
}
