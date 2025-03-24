package org.example.springmvcmapper.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.springmvcmapper.models.Order;
import org.example.springmvcmapper.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Validated
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/getById/{id}")
    public ResponseEntity<String> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid String order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(order));
    }
}
