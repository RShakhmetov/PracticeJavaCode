package org.example.springmvcmapper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springmvcmapper.models.Product;
import org.example.springmvcmapper.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<String> getProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid String productJson) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(productJson));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody @Valid String product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(id, product));
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
    }
}
