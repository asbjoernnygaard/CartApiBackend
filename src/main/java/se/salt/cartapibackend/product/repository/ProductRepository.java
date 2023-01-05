package se.salt.cartapibackend.product.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import se.salt.cartapibackend.product.model.Product;

@Repository
public class ProductRepository {

    @Autowired
    IProductRepository repo;


    public Product addProductToRepo(Product newProduct) {
        return repo.save(newProduct);
    }

    public Product getProductById(String productId) {
        return repo.findById(productId).orElseThrow();
    }
}
