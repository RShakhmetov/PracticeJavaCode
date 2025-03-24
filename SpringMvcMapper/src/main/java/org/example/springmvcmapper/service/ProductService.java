package org.example.springmvcmapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.springmvcmapper.models.Product;
import org.example.springmvcmapper.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ObjectMapper objectMapper;

    public String getAllProducts() {
        List<Product> products = productRepository.findAll();
        try {
            return objectMapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            Product product = productRepository.findById(id).orElse(null);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(product);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Product not found");
        }

    }

    public Product addProduct(String product) {
        Product newProduct = new Product();
        try {
            newProduct = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return productRepository.save(newProduct);
    }

    public Product updateProduct(Long id, String product) {
        Product product1 = new Product();
        try {
            product1 = objectMapper.readValue(product, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (productRepository.findById(id).isPresent()) {
            Product oldProduct = productRepository.findById(id).get();
            oldProduct.setDescription(product1.getDescription());
            oldProduct.setName(product1.getName());
            oldProduct.setPrice(product1.getPrice());
            oldProduct.setQuantityInStock(product1.getQuantityInStock());
            return productRepository.save(oldProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public Product deleteProduct(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Product product1 = productRepository.findById(id).orElse(null);
        productRepository.deleteById(id);
        return product1;
    }
}
