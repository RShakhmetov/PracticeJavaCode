package org.example.springsecurityjwt.service;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.model.Product;
import org.example.springsecurityjwt.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(Product productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
