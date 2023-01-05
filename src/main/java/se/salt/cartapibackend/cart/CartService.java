package se.salt.cartapibackend.cart;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.cartapibackend.cart.model.*;
import se.salt.cartapibackend.cart.repository.CartRepository;
import se.salt.cartapibackend.product.model.Product;
import se.salt.cartapibackend.product.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    CartRepository repo;

    @Autowired
    ProductRepository productRepo;

    public Cart createEmptyCart() {
        return repo.createEmptyCart();
    }

    public Cart getCartById(String cartId) {
        return repo.getCartById(cartId);
    }

    public void deleteCartById(String cartId) {
        repo.deleteCartById(cartId);
    }

    public Cart addCartProductToCart(String cartId, AddCartProductDTO dto) {
        ModelMapper mapper = new ModelMapper();
        Product product = productRepo.getProductById(dto.getId());
        CartProduct cartProductToBeAdded = mapper.map(product, CartProduct.class);
        cartProductToBeAdded.setQuantity(dto.getQuantity());

        Cart cart = repo.getCartById(cartId);
        cart.addCartProductToCart(cartProductToBeAdded);

        return repo.updateCart(cart);
    }

    public Cart removeCartProductFromCart(String cartId, String cartProductId) {
        Cart cart = repo.getCartById(cartId);
        cart.removeCartProductFromCart(cartProductId);

        return repo.updateCart(cart);
    }
}
