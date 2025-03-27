package org.example.springsecurityjwt.controller;

import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.model.Product;
import org.example.springsecurityjwt.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/superadmin/addProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product registerProduct) {
        return ResponseEntity.ok(productService.create(registerProduct));
    }

    @GetMapping("/public/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/user/only")
    public ResponseEntity<Object> userOnly() {
        return ResponseEntity.ok("Only user can access this API");
    }

    @GetMapping("/moderator/only")
    public ResponseEntity<Object> moderator() {
        return ResponseEntity.ok("Only moderator can access this API");
    }
}
