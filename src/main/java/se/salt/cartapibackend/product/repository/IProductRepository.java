package se.salt.cartapibackend.product.repository;

import org.springframework.data.repository.CrudRepository;
import se.salt.cartapibackend.product.model.Product;

public interface IProductRepository extends CrudRepository<Product, String> {
}
