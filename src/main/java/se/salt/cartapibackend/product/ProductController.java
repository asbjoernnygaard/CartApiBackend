package se.salt.cartapibackend.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.salt.cartapibackend.product.model.*;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    ResponseEntity<Void> createProduct(@RequestBody CreateProductDTO dto){
        Product newProduct = service.createProduct(dto);
        return ResponseEntity.created(URI.create("/product/" + newProduct.getId())).build();
    }

    @GetMapping("/{productId}")
    ResponseEntity<?> getProductById(@PathVariable String productId) {
        try {
            Product product = service.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
