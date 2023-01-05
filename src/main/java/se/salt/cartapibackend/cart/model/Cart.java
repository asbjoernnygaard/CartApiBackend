package se.salt.cartapibackend.cart.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

    @Id
    final String id = UUID.randomUUID().toString();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id")
    List<CartProduct> cartProductList = new ArrayList<>();

    @Column
    Double totalPrice = 0.0;

    private void updateTotalPrice(){
        totalPrice = cartProductList.stream().reduce(0.0, (subtotal, cartProduct) ->
                subtotal + cartProduct.getPrice() * cartProduct.getQuantity(), Double::sum);
    }

    public void addCartProductToCart(CartProduct addedCartProduct){
        int addedCartProductIndex = cartProductList.indexOf(addedCartProduct);
        if(addedCartProductIndex == -1) {
            cartProductList.add(addedCartProduct);
        } else {
            cartProductList.get(addedCartProductIndex).addToQuantity(addedCartProduct.getQuantity());
        }

        updateTotalPrice();
    }

    public void removeCartProductFromCart(String cartProductId){
        CartProduct cartProductToBeRemoved = cartProductList.stream()
                .filter(e -> e.getId().equals(cartProductId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Given id matches no products in cart"));

        cartProductList.remove(cartProductToBeRemoved);
        updateTotalPrice();
    }
}
