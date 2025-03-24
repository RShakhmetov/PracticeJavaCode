package org.example.springmvcmapper.repositories;

import org.example.springmvcmapper.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
