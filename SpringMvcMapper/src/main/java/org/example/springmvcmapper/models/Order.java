package org.example.springmvcmapper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Product> products;

    @DateTimeFormat
    private LocalDate orderDate;

    @NotNull(message = "shippingAddress must`t be empty")
    private String shippingAddress;

    @PositiveOrZero(message = "Price can`t be negative")
    private double totalPrice;

    @NotNull
    private String orderStatus;

}
