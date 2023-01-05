package se.salt.cartapibackend.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salt.cartapibackend.product.model.*;
import se.salt.cartapibackend.product.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository repo;

    ModelMapper mapper = new ModelMapper();

    public Product createProduct(CreateProductDTO dto) {
        System.out.println(dto);
        Product newProduct = mapper.map(dto, Product.class);
        System.out.println(newProduct);
        return repo.addProductToRepo(newProduct);
    }

    public Product getProductById(String productId) {
        return repo.getProductById(productId);
    }
}
