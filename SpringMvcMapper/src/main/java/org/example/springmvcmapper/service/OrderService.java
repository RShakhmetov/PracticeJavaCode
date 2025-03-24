package org.example.springmvcmapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.springmvcmapper.models.Customer;
import org.example.springmvcmapper.models.Order;
import org.example.springmvcmapper.repositories.CustomerRepository;
import org.example.springmvcmapper.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;

    public String getOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            Order order = orderRepository.findById(id).get();
            try {
                return objectMapper.writeValueAsString(order);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    public Order addOrder(String order) {
        Order order1;
        try {
            order1 = objectMapper.readValue(order, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Customer addedCustomer = customerRepository.findByEmail(order1.getCustomer().getEmail());
        if (addedCustomer != null) {
            order1.setCustomer(addedCustomer);
        } else {
            customerRepository.save(order1.getCustomer());
        }
        return orderRepository.save(order1);
    }
}
