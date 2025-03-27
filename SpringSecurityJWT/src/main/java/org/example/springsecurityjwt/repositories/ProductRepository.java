package org.example.springsecurityjwt.repositories;

import org.example.springsecurityjwt.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
