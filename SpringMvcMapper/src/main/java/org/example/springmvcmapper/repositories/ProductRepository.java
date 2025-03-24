package org.example.springmvcmapper.repositories;

import org.example.springmvcmapper.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
